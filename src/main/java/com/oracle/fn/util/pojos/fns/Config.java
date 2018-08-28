
package com.oracle.fn.util.pojos.fns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @SerializedName("DB_PASSWORD")
    @Expose
    private String dBPASSWORD;
    @SerializedName("DB_URL")
    @Expose
    private String dBURL;
    @SerializedName("DB_USER")
    @Expose
    private String dBUSER;

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
