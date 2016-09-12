package com.pactsafe.api.activity;

import com.pactsafe.api.activity.components.PactSafeActivityException;
import com.pactsafe.api.activity.domain.ParameterStore;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * created by Michael Welling on 8/22/16.
 */
public class Group {

    private ParameterStore parameters;
    private Activity client;
    private String key;

    public Group(Activity client, String key) {
        this(client, key,  null);
    }

    public Group(Activity client, String key, ParameterStore parameters) {
        if (client == null) {
            throw new IllegalArgumentException("You must pass in an Activity client.");
        }
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("You must pass in a valid group key.");
        }
        this.client = client;
        this.key = key;
        this.parameters = parameters == null ? new ParameterStore() : parameters;
        this.parameters.setKey(key);
    }

    public Map<Integer, String> retrieve(String signerId) throws PactSafeActivityException {
        return client.retrieve(signerId, parameters.getContracts());
    }

    public Map<Integer, Boolean> latest(String signerId) throws PactSafeActivityException {
        return client.latest(signerId, parameters.getContracts());
    }

    /** GETTERS AND SETTERS **/
    public ParameterStore getParameters() {
        return parameters;
    }

    public void setParameters(ParameterStore parameters) {
        this.parameters = parameters;
    }
}
