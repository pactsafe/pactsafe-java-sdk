package com.pactsafe.api.activity.components;

/**
 * Created by Michael Welling on 8/25/16.
 */
public class PactSafeActivityException extends Exception {

    public PactSafeActivityException(String action, int code, String message) {
        super("Error: " + code + " " + message + ". Action: " + action);
    }

    public PactSafeActivityException(String message, Throwable e) {
        super(message, e);
    }
}
