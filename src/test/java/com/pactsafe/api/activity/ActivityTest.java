package com.pactsafe.api.activity;

import static org.mockito.Mockito.*;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pactsafe.api.activity.components.ActivityAPIClient;
import com.pactsafe.api.activity.domain.ParameterStore;
import com.pactsafe.api.activity.domain.EventType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Michael Welling on 8/24/16.
 */
@Ignore
public class ActivityTest {

    private static final String ACCESS_KEY = "access_key";
    private static final String SIGNER_ID = "signer.id@pactsafe.com";
    private static final String GROUP_KEY = "ps-contract-group";

    private Activity activity;
    private ActivityAPIClient clientMock = mock(ActivityAPIClient.class);

    @Before
    public void setUp() throws Exception {
        activity = new Activity(ACCESS_KEY, null, null, clientMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_failureNullAction() throws Exception {
        activity.send(EventType.AGREED, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_failureNoContracts() throws Exception {
        ParameterStore store = new ParameterStore();
        store.setSignerId(SIGNER_ID);

        activity.send(EventType.AGREED, store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_failureNoSignerId() throws Exception {
        ParameterStore store = new ParameterStore();
        store.setContracts(Lists.newArrayList(1,2));

        activity.send(EventType.AGREED, store);
    }

    @Test
    public void send_success() throws Exception {
        ParameterStore store = new ParameterStore();
        store.setSignerId(SIGNER_ID);
        store.setContracts(Lists.newArrayList(1,2));

        activity.send(EventType.AGREED, store);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retrieve_failureNoSignerId() throws Exception {
        activity.retrieve(" ", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retrieve_failureNoContracts() throws Exception {
        activity.retrieve(SIGNER_ID, Lists.<Integer>newArrayList());
    }

    @Test
    public void retrieve_success() throws Exception {
        String versionId = "versionId";
        Map<Integer, String> data = Maps.newHashMap(ImmutableMap.of(1, versionId));
        when(clientMock.retrieve(any(ParameterStore.class))).thenReturn(data);

        Map<Integer, String> output = activity.retrieve(SIGNER_ID, Lists.<Integer>newArrayList(1));

        assertTrue(output.containsValue(versionId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void latest_failureNoSignerId() throws Exception {
        activity.latest(null, Lists.<Integer>newArrayList(1,2,3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void latest_failureNoContracts() throws Exception {
        activity.latest(SIGNER_ID, Lists.<Integer>newArrayList());
    }

    @Test
    public void latest_success() throws Exception {
        Map<Integer, Boolean> data = Maps.newHashMap(ImmutableMap.of(1, true));
        when(clientMock.latest(any(ParameterStore.class))).thenReturn(data);

        Map<Integer, Boolean> output = activity.latest(SIGNER_ID, Lists.<Integer>newArrayList(1,2));

        assertTrue(output.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void load_failureNoGroupKey() throws Exception {
        activity.load(null);
    }

    @Test
    public void load_success() throws Exception {
        String contractHTML = "<div>Hello World</div>";
        ParameterStore data = new ParameterStore();
        data.setContractHtml(contractHTML);
        when(clientMock.load(any(ParameterStore.class))).thenReturn(data);

        Group output = activity.load(GROUP_KEY);

        assertTrue(output != null);
        assertTrue(contractHTML.equals(output.getParameters().getContractHtml()));
    }

}