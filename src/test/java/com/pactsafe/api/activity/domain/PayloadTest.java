package com.pactsafe.api.activity.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Michael Welling on 8/25/16.
 */
public class PayloadTest {

    private static final String VERSIONS = "v=1&_v=ps1&";
    private static final String NONCE = "&nc=";
    private static final String SERVER = "&_noerr=0&_srv=1";
    private static final String CLIENT_LIBRARY = "&_lib";
    private static final String CLIENT_VERSION = "&_libv=";

    @Test
    public void toString_IgnoreNullValues() throws Exception {

        ParameterStore parameters = new ParameterStore();
        String output = new Payload(parameters).toString();

        assertTrue(output.contains(VERSIONS));
        assertTrue(output.contains(SERVER));
        assertTrue(output.contains(CLIENT_LIBRARY));
        assertTrue(output.contains(CLIENT_VERSION));
        assertTrue(output.contains(NONCE));
    }

    @Test
    public void toString_URLEncodeString() throws Exception {

        String testString = "sig=example%40pactsafe.com&pat=My%20Page%20Title";

        ParameterStore parameters = new ParameterStore();
        parameters.setSignerId("example@pactsafe.com");
        parameters.setPageTitle("My Page Title");

        String output = new Payload(parameters).toString();

        assertTrue(output.contains(testString));
        assertTrue(output.contains(VERSIONS));
        assertTrue(output.contains(SERVER));
        assertTrue(output.contains(CLIENT_LIBRARY));
        assertTrue(output.contains(CLIENT_VERSION));
        assertTrue(output.contains(NONCE));
    }

    @Test
    public void toString_URLEncodeArrary() throws Exception {

        String testString = "cid=1%2C2%2C3";

        ParameterStore parameters = new ParameterStore();
        parameters.setContracts(Lists.newArrayList(1,2,3));
        String output = new Payload(parameters).toString();

        assertTrue(output.contains(testString));
        assertTrue(output.contains(VERSIONS));
        assertTrue(output.contains(SERVER));
        assertTrue(output.contains(CLIENT_LIBRARY));
        assertTrue(output.contains(CLIENT_VERSION));
        assertTrue(output.contains(NONCE));
    }

    @Test
    public void toString_URLEncodeBoolean() throws Exception {

        String testString = "bje=0&cnf=1";

        ParameterStore parameters = new ParameterStore();
        parameters.setConfirmationEmail(true);
        parameters.setJavaEnabled(false);
        String output = new Payload(parameters).toString();

        assertTrue(output.contains(testString));
        assertTrue(output.contains(VERSIONS));
        assertTrue(output.contains(SERVER));
        assertTrue(output.contains(CLIENT_LIBRARY));
        assertTrue(output.contains(CLIENT_VERSION));
        assertTrue(output.contains(NONCE));
    }

    @Test
    public void toString_URLEncodeMap() throws Exception {

        String testString = "rnd=%7B%22key1%22%3A%22example%40pactsafe.com%22%2C%22key2%22%3A%5B1%2C2%5D%2C%22key3%22%3Atrue%7D";

        ParameterStore parameters = new ParameterStore();
        Map<String, Object> data = Maps.newHashMap();
        data.put("key1", "example@pactsafe.com");
        data.put("key2", Lists.newArrayList(1,2));
        data.put("key3", true);
        parameters.setRenderData(data);
        String output = new Payload(parameters).toString();

        assertTrue(output.contains(testString));
        assertTrue(output.contains(VERSIONS));
        assertTrue(output.contains(SERVER));
        assertTrue(output.contains(CLIENT_LIBRARY));
        assertTrue(output.contains(CLIENT_VERSION));
        assertTrue(output.contains(NONCE));
    }

    @Test
    public void toString_URLEncodeEventType() throws Exception {

        String testString = "v=1&_v=ps1&et=agreed";

        ParameterStore parameters = new ParameterStore();
        parameters.setEventType(EventType.AGREED);
        String output = new Payload(parameters).toString();

        assertTrue(output.contains(testString));
        assertTrue(output.contains(VERSIONS));
        assertTrue(output.contains(SERVER));
        assertTrue(output.contains(CLIENT_LIBRARY));
        assertTrue(output.contains(CLIENT_VERSION));
        assertTrue(output.contains(NONCE));
    }

}