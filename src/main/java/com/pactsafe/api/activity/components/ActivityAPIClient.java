package com.pactsafe.api.activity.components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pactsafe.api.activity.domain.ParameterStore;
import com.pactsafe.api.activity.domain.Payload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * created by Michael Welling on 8/22/16.
 */
public class ActivityAPIClient {

    private String baseUrl;
    private ObjectMapper mapper;

    public ActivityAPIClient() {
        this.mapper = new ObjectMapper();
    }

    private String request(String path, String postData) throws PactSafeActivityException {
        try {
            byte[] postDataBytes = postData.getBytes("UTF-8");
            int postDataLength = postDataBytes.length;
            String request = baseUrl + path;
            URL url = new URL(request);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.getOutputStream().write(postDataBytes);

            if (conn.getResponseCode() >= 400) {
                throw new PactSafeActivityException(path, conn.getResponseCode(), conn.getResponseMessage());
            }

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);

            return sb.toString();

        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete action for " + path, e);
        }
    }

    public void send(ParameterStore parameters) throws PactSafeActivityException {
        String postData = new Payload(parameters).toString();
        String path = "/send";
        this.request(path, postData);
    }

    public Map<Integer, String> retrieve(ParameterStore parameters) throws PactSafeActivityException {
        try {
            String postData = new Payload(parameters).toString();
            String path = "/retrieve";
            String response = this.request(path, postData);
            return mapper.readValue(response, new TypeReference<Map<Integer, String>>(){});
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete retrieve action.", e);
        }
    }

    public Map<Integer, Boolean> latest(ParameterStore parameters) throws PactSafeActivityException {
        try {
            String postData = new Payload(parameters).toString();
            String path = "/latest";
            String response = this.request(path, postData);
            return mapper.readValue(response, new TypeReference<Map<Integer, Boolean>>(){});
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete latest action.", e);
        }
    }

    public ParameterStore load(ParameterStore parameters) throws PactSafeActivityException {
        try {
            String postData = new Payload(parameters).toString();
            String path = "/load/json";
            String response = this.request(path, postData);
            return mapper.readValue(response, ParameterStore.class);
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete load action.", e);
        }
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
