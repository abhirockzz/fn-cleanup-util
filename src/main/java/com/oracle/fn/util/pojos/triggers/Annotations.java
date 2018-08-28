
package com.oracle.fn.util.pojos.triggers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annotations {

    @SerializedName("fnproject.io/trigger/httpEndpoint")
    @Expose
    private String fnprojectIoTriggerHttpEndpoint;
    @SerializedName("oracle.com/oci/compartmentId")
    @Expose
    private String oracleComOciCompartmentId;

    public String getFnprojectIoTriggerHttpEndpoint() {
        return fnprojectIoTriggerHttpEndpoint;
    }

    public void setFnprojectIoTriggerHttpEndpoint(String fnprojectIoTriggerHttpEndpoint) {
        this.fnprojectIoTriggerHttpEndpoint = fnprojectIoTriggerHttpEndpoint;
    }

    public String getOracleComOciCompartmentId() {
        return oracleComOciCompartmentId;
    }

    public void setOracleComOciCompartmentId(String oracleComOciCompartmentId) {
        this.oracleComOciCompartmentId = oracleComOciCompartmentId;
    }

}
