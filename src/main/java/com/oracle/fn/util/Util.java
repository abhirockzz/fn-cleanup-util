package com.oracle.fn.util;

import com.oracle.fn.FnHTTPGet;
import com.oracle.fn.FnHTTPDelete;
import com.google.gson.Gson;

import com.oracle.fn.util.pojos.app.Apps;
import com.oracle.fn.util.pojos.app.Item;
import com.oracle.fn.util.pojos.fns.Functions;
import com.oracle.fn.util.pojos.routes.Route;
import com.oracle.fn.util.pojos.routes.Routes;
import com.oracle.fn.util.pojos.triggers.Triggers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

public class Util {

    static Gson gson = new Gson();
    final static String FN_API_BASE_ENDPOINT = "https://api.dev.us-ashburn-1.functions.oci.oraclecloud.com";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java -jar fn-util-1.0.jar  <path_to_properties_config_file>");
            return;
        }

        String propertiesFileLocation = args[0];
        System.out.println("Configuration properties file location " + propertiesFileLocation);

        Properties props = new Properties();
        props.load(new FileInputStream(propertiesFileLocation));

        String tenancyOCID = props.getProperty("root_tenancy_ocid");
        System.out.println("Root tenancy OCID " + tenancyOCID);
        
        String publicKeyFingerprint = props.getProperty("public_key_fingerprint");
        System.out.println("Fingerprint " + publicKeyFingerprint);
        
        String usrOCID = props.getProperty("user_ocid");
        System.out.println("User OCID " + tenancyOCID);
        
        String privateKeyFilename = props.getProperty("private_key_file_path");
        System.out.println("Private key " + privateKeyFilename);

        String compartmentID = props.getProperty("compartment_id");
        System.out.println("Compartment ID " + compartmentID);
        
        FnHTTPGet fnGET = new FnHTTPGet(tenancyOCID, usrOCID, publicKeyFingerprint, privateKeyFilename, compartmentID);
        FnHTTPDelete fnDelete = new FnHTTPDelete(tenancyOCID, usrOCID, publicKeyFingerprint, privateKeyFilename, compartmentID);

        String endpoint = FN_API_BASE_ENDPOINT+"/v2/apps";

        //get all the apps in compartment
        String appsJSON = fnGET.invoke(endpoint);
        Apps apps = gson.fromJson(appsJSON, Apps.class);
        System.out.println("Listing Applications....");

        for (Item app : apps.getItems()) {
            System.out.println(app.getName());
        }

        System.out.println("The above apps and its artifacts (functions, triggers and routes) will be DELETED. Enter yes to proceed, else the process will be terminated");
        Scanner prompt = new Scanner(System.in);
        String yesOrNo = prompt.nextLine();

        if (!yesOrNo.equalsIgnoreCase("yes")) {
            System.out.println("Deletion process will NOT proceed further");
            return;
        }

        for (Item app : apps.getItems()) {
            try {
                System.out.println("App " + app.getName());

                String appID = app.getId();

                //get All functions for an app
                endpoint = FN_API_BASE_ENDPOINT+"/v2/fns?app_id=" + appID;
                String functionsJSON = fnGET.invoke(endpoint);
                Functions fns = gson.fromJson(functionsJSON, Functions.class);

                for (com.oracle.fn.util.pojos.fns.Item function : fns.getItems()) {
                    try {
                        System.out.println("Function " + function.getName());
                        String fnID = function.getId();

                        //get All triggers for an app+fn combo
                        endpoint = FN_API_BASE_ENDPOINT+"/v2/triggers?app_id=" + appID + "&fn_id=" + fnID;
                        String triggersJSON = fnGET.invoke(endpoint);
                        Triggers triggers = gson.fromJson(triggersJSON, Triggers.class);

                        for (com.oracle.fn.util.pojos.triggers.Item trigger : triggers.getItems()) {
                            try {
                                System.out.println("Deleting Trigger " + trigger.getName());

                                //delete the trigger
                                endpoint = FN_API_BASE_ENDPOINT+"/v2/triggers/" + trigger.getId();
                                String delStatus = fnDelete.invoke(endpoint, new DeleteRespHandler());
                                String delMsg = delStatus.equals("204") ? "Successfully deleted trigger " + trigger.getName() : "Failed to delete trigger " + trigger.getName();
                                System.out.println(delMsg);
                            } catch (Exception e) {
                                e.printStackTrace(); //log and continue
                            }

                        }

                        System.out.println("Deleting Function " + function.getName());
                        endpoint = FN_API_BASE_ENDPOINT+"/v2/fns/" + fnID;
                        String delStatus = fnDelete.invoke(endpoint, new DeleteRespHandler());
                        //System.out.println("Function del status "+ delStatus);
                        String delMsg = delStatus.equals("204") ? "Successfully deleted function " + function.getName() : "Failed to delete function " + function.getName();
                        System.out.println(delMsg);
                    } catch (Exception e) {
                        e.printStackTrace(); //log and continue
                    }

                }

                endpoint = FN_API_BASE_ENDPOINT+"/v1/apps/" + app.getName() + "/routes";
                String routesJSON = fnGET.invoke(endpoint);
                Routes routes = gson.fromJson(routesJSON, Routes.class);
                if (routes.getRoutes() != null) {
                    for (Route route : routes.getRoutes()) {
                        try {
                            System.out.println("Deleting route " + route.getPath());
                            endpoint = FN_API_BASE_ENDPOINT+"/v1/apps/" + app.getName() + "/routes/" + route.getPath();
                            String delStatus = fnDelete.invoke(endpoint, new DeleteRespHandler());
                            String delMsg = delStatus.equals("200") ? "Successfully deleted route " + route.getPath() : "Failed to delete route " + route.getPath();
                            System.out.println(delMsg);
                        } catch (Exception e) {
                            e.printStackTrace(); //log and continue
                        }

                    }
                }

                System.out.println("Deleting App " + app.getName());
                endpoint = FN_API_BASE_ENDPOINT+"/v2/apps/" + appID;
                String delStatus = fnDelete.invoke(endpoint, new DeleteRespHandler());
               // System.out.println("App del status "+ delStatus);
                String delMsg = delStatus.equals("204") ? "Successfully deleted App " + app.getName() : "Failed to delete app " + app.getName();
                System.out.println(delMsg);
            } catch (Exception e) {
                e.printStackTrace(); //log and continue
            }

        }

    }

    static class DeleteRespHandler implements ResponseHandler<String> {

        @Override
        public String handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
            return String.valueOf(hr.getStatusLine().getStatusCode());
        }

    }
}
