package com.pactsafe.api.activity;

import com.pactsafe.api.activity.components.ActivityAPIClient;
import com.pactsafe.api.activity.components.PactSafeActivityException;
import com.pactsafe.api.activity.domain.ActivityOptions;
import com.pactsafe.api.activity.domain.ParameterStore;
import com.pactsafe.api.activity.domain.EventType;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * created by Michael Welling on 8/22/16.
 */
public class Activity {

    private String accessKeyId;
    private ParameterStore parameters;
    private ActivityOptions options;
    private ActivityAPIClient client;

    /**
     * <p>
     * Initializes a new Activity object to interface with the PactSafe Response API.
     * For those familiar with our web snippet, this object mimics the 'site' object
     * found there. At the very least it must be initialized with your PactSafe API
     * Access ID, found under your account settings on http://app.pactsafe.com.
     * </p>
     *
     * @param accessKeyId Your Access ID for your site. Example: 5cfb45a9-2289-4429-97c0-5b3589250eaa
     */
    public Activity(String accessKeyId) {
        this(accessKeyId, null, null, new ActivityAPIClient());
    }

    /**
     * <p>
     * Initializes a new Activity object to interface with the PactSafe Response API.
     * For those familiar with our web snippet, this object mimics the 'site' object
     * found there. At the very least it must be initialized with your PactSafe API
     * Access ID, found under your account settings on http://app.pactsafe.com.
     * </p>
     *
     * @param accessKeyId Your Access ID for your site (ex: 5cfb45a9-2289-4429-97c0-5b3589250eaa).
     *
     * @param parameters ParameterStore object containing any global parameters for the
     *                   site and future default requests to the API
     */
    public Activity(String accessKeyId, ParameterStore parameters) {
        this(accessKeyId, parameters, null, new ActivityAPIClient());
    }

    /**
     * <p>
     * Initializes a new Activity object to interface with the PactSafe Response API.
     * For those familiar with our web snippet, this object mimics the 'site' object
     * found there. At the very least it must be initialized with your PactSafe API
     * Access ID, found under your account settings on http://app.pactsafe.com.
     * </p>
     *
     * @param accessKeyId Your Access ID for your site (ex: 5cfb45a9-2289-4429-97c0-5b3589250eaa).
     *
     * @param parameters ParameterStore object containing any global parameters for the
     *                   site and future default requests to the API
     *
     * @param options ActivityOptions object containing global configuration options.
     */
    public Activity(String accessKeyId, ParameterStore parameters, ActivityOptions options) {
        this(accessKeyId, parameters, options, new ActivityAPIClient());
    }

    protected Activity(String accessKeyId, ParameterStore parameters, ActivityOptions options, ActivityAPIClient client) {
        if (StringUtils.isBlank(accessKeyId)) {
            throw new IllegalArgumentException("You must pass your PactSafe Site's Access ID.");
        }
        this.accessKeyId = accessKeyId;
        this.options = options == null ? new ActivityOptions() : options;
        this.parameters = parameters == null ? new ParameterStore() : parameters;
        this.parameters.setSiteId(accessKeyId);

        this.client = client;
        this.client.setBaseUrl(this.options.getHost());
    }

    /**
     * <p>
     * Sends an 'Agreed" event to the PactSafe Activity API for a given signer and contract(s).
     * </p>
     *
     * @param action ParameterStore object containing any additional parameters for agreement
     *               action. `signerId` and `contracts` are required to be set.
     *
     * @return The string representation of the web beacon returned, or null on failure
     */
    public void agreed(ParameterStore action) throws PactSafeActivityException {
        send(EventType.AGREED, action);
    }

    /**
     * <p>
     * Sends an action event to the PactSafe Activity API for a given signer and contract(s).
     * </p>
     *
     * @param eventType EventType object indicating the action to send to the API. One of
     *                  `AGREED`, `DISAGREED`, `DISPLAYED`, `VISITED`, `UPDATED`.
     *
     * @param action ParameterStore object containing any additional parameters for agreement
     *               action. `signerId` and `renderId` are required to be set.
     *
     * @return The string representation of the web beacon returned, or null on failure
     */
    public void send(EventType eventType, ParameterStore action) throws PactSafeActivityException {
        if (eventType == null) {
            throw new IllegalArgumentException("You must pass an EventType object.");
        }
        if (action == null) {
            throw new IllegalArgumentException("You must pass in a ParameterStore object.");
        }
        if (StringUtils.isBlank(action.getSignerId())) {
            throw new IllegalArgumentException("You must set a signerId on the action.");
        }
        if (StringUtils.isBlank(action.getRenderId())) {
            throw new IllegalArgumentException("You must set a renderId for this action.");
        }

        ParameterStore toSend = new ParameterStore(parameters, action);
        toSend.setEventType(eventType);
        if (StringUtils.isBlank(toSend.getTimeStamp())) toSend.setTimeStamp(new Date().toString());
        if (StringUtils.isBlank(toSend.getActionId())) {
            toSend.setActionId("node-" + new BigInteger(130, new SecureRandom()).toString(32));
        }

        client.send(toSend);
    }

    /**
     * <p>
     * Retrieves information that indicates the most recently accepted versions of a contract(s)
     * by a given signer.
     * </p>
     *
     * @param signerId The `signerId` for which to retrieve acceptance information.
     *
     * @param contracts The list of contractIds for which to check the latest accepted version
     *
     * @return A Map<Integer,String> of {contractId:versionId}
     */
    public Map<Integer, String> retrieve(String signerId, List<Integer> contracts) throws PactSafeActivityException {
        if (StringUtils.isBlank(signerId)) {
            throw new IllegalArgumentException("You must pass in a signerId");
        }

        ParameterStore toSend = new ParameterStore(parameters);
        toSend.setEventType(EventType.RETRIEVE);
        toSend.setSignerId(signerId);
        toSend.setContracts(contracts);

        return client.retrieve(toSend);
    }

    /**
     * <p>
     * Retrieves information that indicates if a given signer has accepted the most recent
     * version of a contract.
     * </p>
     *
     * @param signerId The `signerId` for which to retrieve acceptance information.
     *
     * @param contracts The list of contractIds for which to check the latest accepted version
     *
     * @return A Map<Integer,Boolean> of {contractId:accepted} for the most recent contract version
     */
    public Map<Integer, Boolean> latest(String signerId, List<Integer> contracts) throws PactSafeActivityException {
        if (StringUtils.isBlank(signerId)) {
            throw new IllegalArgumentException("You must pass in a signerId");
        }

        ParameterStore toSend = new ParameterStore(parameters);
        toSend.setEventType(EventType.LATEST);
        toSend.setSignerId(signerId);
        toSend.setContracts(contracts);

        return client.latest(toSend);
    }

    /**
     * <p>
     * Loads a given contract group from the PactSafe app for a given `groupKey`.
     * </p>
     *
     * @param groupKey The key for the contract group set up in the PactSafe app.
     *
     * @return A Group object for given key. Also accessible on the `groups` property of the Activity.
     */
    public Group load(String groupKey) throws PactSafeActivityException {
        return load(groupKey, null);
    }

    /**
     * <p>
     * Loads a given contract group from the PactSafe app for a given `groupKey`.
     * Additional `render_data` can also be included for a dynamic contract.
     * </p>
     *
     * @param groupKey The key for the contract group set up in the PactSafe app.
     *
     * @param renderData Additional data fields needed to display the contract.
     *
     * @return A Group object for given key. Also accessible on the `groups` property of the Activity.
     */
    public Group load(String groupKey, Map<String, Object> renderData) throws PactSafeActivityException {
        if (StringUtils.isBlank(groupKey)) {
            throw new IllegalArgumentException("You must pass in a group key.");
        }

        ParameterStore toSend = new ParameterStore(parameters);
        toSend.setEventType(EventType.LOAD);
        toSend.setKey(groupKey);
        toSend.setRenderData(renderData);

        ParameterStore store = client.load(toSend);
        return new Group(this, groupKey, store);
    }

    public ParameterStore getParameters() {
        return parameters;
    }

    public void setParameters(ParameterStore parameters) {
        this.parameters = parameters;
    }
}
