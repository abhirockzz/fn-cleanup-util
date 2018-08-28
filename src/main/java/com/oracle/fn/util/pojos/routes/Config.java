
package com.oracle.fn.util.pojos.routes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @SerializedName("NAMESPACE")
    @Expose
    private String nAMESPACE;
    @SerializedName("OCI_PRIVATE_KEY_FILE_NAME")
    @Expose
    private String oCIPRIVATEKEYFILENAME;
    @SerializedName("DB_PASSWORD")
    @Expose
    private String dBPASSWORD;
    @SerializedName("DB_URL")
    @Expose
    private String dBURL;
    @SerializedName("DB_USER")
    @Expose
    private String dBUSER;

    public String getNAMESPACE() {
        return nAMESPACE;
    }

    public void setNAMESPACE(String nAMESPACE) {
        this.nAMESPACE = nAMESPACE;
    }

    public String getOCIPRIVATEKEYFILENAME() {
        return oCIPRIVATEKEYFILENAME;
    }

    public void setOCIPRIVATEKEYFILENAME(String oCIPRIVATEKEYFILENAME) {
        this.oCIPRIVATEKEYFILENAME = oCIPRIVATEKEYFILENAME;
    }

    public String getDBPASSWORD() {
        return dBPASSWORD;
    }

    public void setDBPASSWORD(String dBPASSWORD) {
        this.dBPASSWORD = dBPASSWORD;
    }

    public String getDBURL() {
        return dBURL;
    }

    public void setDBURL(String dBURL) {
        this.dBURL = dBURL;
    }

    public String getDBUSER() {
        return dBUSER;
    }

    public void setDBUSER(String dBUSER) {
        this.dBUSER = dBUSER;
    }

}
