package com.pactsafe.api.activity.domain;

/**
 * created by Michael Welling on 8/22/16.
 */
public enum EventType {
    AGREED("agreed"),
    DISAGREED("disagreed"),
    DISPLAYED("displayed"),
    VISITED("visited"),
    UPDATED("updated"),
    LOAD("load"),
    RETRIEVE("retrieve"),
    LATEST("latest");

    private String eventName;

    EventType(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
