package com.pactsafe.api.activity.domain;

/**
 * Created by Michael Welling on 8/23/16.
 */
public class ActivityOptions {

    /**
     *  The object used to configure global options for the Activity client at initialization.
     *
     *  Currently only `host` is offered as an override option, but this class could be
     *  expanded in the future to include additional functionality.
     */

    private String host = "https://pactsafe.io";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
