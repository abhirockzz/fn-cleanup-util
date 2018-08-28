
package com.oracle.fn.util.pojos.fns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annotations {

    @SerializedName("fnproject.io/fn/invokeEndpoint")
    @Expose
    private String fnprojectIoFnInvokeEndpoint;
    @SerializedName("oracle.com/oci/compartmentId")
    @Expose
    private String oracleComOciCompartmentId;

    public String getFnprojectIoFnInvokeEndpoint() {
        return fnprojectIoFnInvokeEndpoint;
    }

    public void setFnprojectIoFnInvokeEndpoint(String fnprojectIoFnInvokeEndpoint) {
        this.fnprojectIoFnInvokeEndpoint = fnprojectIoFnInvokeEndpoint;
    }

    public String getOracleComOciCompartmentId() {
        return oracleComOciCompartmentId;
    }

    public void setOracleComOciCompartmentId(String oracleComOciCompartmentId) {
        this.oracleComOciCompartmentId = oracleComOciCompartmentId;
    }

}
