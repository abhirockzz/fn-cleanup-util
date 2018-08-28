
package com.oracle.fn.util.pojos.routes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Routes {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("next_cursor")
    @Expose
    private String nextCursor;
    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
