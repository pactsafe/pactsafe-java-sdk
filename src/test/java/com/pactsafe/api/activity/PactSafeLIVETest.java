package com.pactsafe.api.activity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pactsafe.api.activity.components.PactSafeActivityException;
import com.pactsafe.api.activity.domain.ActivityOptions;
import com.pactsafe.api.activity.domain.EventType;
import com.pactsafe.api.activity.domain.ParameterStore;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Michael Welling on 8/24/16.
 */

@Ignore
public class PactSafeLIVETest {

    private static final String ACCESS_KEY = null;   // YOUR ACCESS KEY HERE
    private static final String GROUP_KEY = null;    // KEY FOR ONE OF YOUR CREATED GROUPS
    private static final String SIGNER_ID = null;    // SIGNER ID TO TEST AGAINST

    @Test
    public void runLiveTest() throws Exception {

        // First we need to initialize the API client with our API Access ID Key
        ActivityOptions options = new ActivityOptions();
        Activity site = new Activity(ACCESS_KEY, new ParameterStore(), options);

        try {
            Group group = site.load(GROUP_KEY);

            // This group will contain a renderId and contractHtml unique to this request for the provided renderData
            // Use this information to present the user their contact!

            // Get back the most recent version IDs that the signer has agreed to for each contract in group
            Map<Integer, String> retrieve = group.retrieve(SIGNER_ID);

            // See if the signer has accepted the most recent versions of each contract in group
            Map<Integer, Boolean> latest = group.latest(SIGNER_ID);
        } catch (PactSafeActivityException e) {
            assertTrue(false);
        }

        // Now our user has performed an action and we are ready to send it
        try {
            Map<String, Object> renderData = Maps.newHashMap();
            renderData.put("signer_id", SIGNER_ID);
            renderData.put("accepted", Lists.newArrayList(1, 2));
            Group group = site.load(GROUP_KEY, renderData);

            ParameterStore action = new ParameterStore();
            action.setSignerId(SIGNER_ID);
            action.setRenderId(group.getParameters().getRenderId());
            action.setPageTitle("My Test Page Title");
            action.setOperatingSystem("Mac OS");
            site.send(EventType.DISPLAYED, action);

            action.setPageTitle("My Test Page Title");
            action.setOperatingSystem("Mac OS");
            action.setTimeStamp(new Date().toString());
            site.agreed(action);
        } catch (PactSafeActivityException e) {
            assertTrue(false);
        }

    }
}
