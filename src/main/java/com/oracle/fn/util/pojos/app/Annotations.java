
package com.oracle.fn.util.pojos.app;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Annotations {

    @SerializedName("oracle.com/oci/appCode")
    @Expose
    private String oracleComOciAppCode;
    @SerializedName("oracle.com/oci/compartmentId")
    @Expose
    private String oracleComOciCompartmentId;
    @SerializedName("oracle.com/oci/subnetIds")
    @Expose
    private List<String> oracleComOciSubnetIds = null;
    @SerializedName("oracle.com/oci/tenantId")
    @Expose
    private String oracleComOciTenantId;

    public String getOracleComOciAppCode() {
        return oracleComOciAppCode;
    }

    public void setOracleComOciAppCode(String oracleComOciAppCode) {
        this.oracleComOciAppCode = oracleComOciAppCode;
    }

    public String getOracleComOciCompartmentId() {
        return oracleComOciCompartmentId;
    }

    public void setOracleComOciCompartmentId(String oracleComOciCompartmentId) {
        this.oracleComOciCompartmentId = oracleComOciCompartmentId;
    }

    public List<String> getOracleComOciSubnetIds() {
        return oracleComOciSubnetIds;
    }

    public void setOracleComOciSubnetIds(List<String> oracleComOciSubnetIds) {
        this.oracleComOciSubnetIds = oracleComOciSubnetIds;
    }

    public String getOracleComOciTenantId() {
        return oracleComOciTenantId;
    }

    public void setOracleComOciTenantId(String oracleComOciTenantId) {
        this.oracleComOciTenantId = oracleComOciTenantId;
    }

 

}
