package com.pactsafe.api.activity.components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pactsafe.api.activity.domain.ParameterStore;
import com.pactsafe.api.activity.domain.Payload;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * created by Michael Welling on 8/22/16.
 */
public class ActivityAPIClient {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");

    private String baseUrl;
    private OkHttpClient client;
    private ObjectMapper mapper;

    public ActivityAPIClient() {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
    }

    public void send(ParameterStore parameters) throws PactSafeActivityException {
        try {
            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
            Request request = new Request.Builder()
                    .url(baseUrl + "/send")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() >= 400) {
                throw new PactSafeActivityException("send", response.code(), response.message());
            }
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete send action.", e);
        }
    }

    public Map<Integer, String> retrieve(ParameterStore parameters) throws PactSafeActivityException {
        try {
            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
            Request request = new Request.Builder()
                    .url(baseUrl + "/retrieve")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() >= 400) {
                throw new PactSafeActivityException("retrieve", response.code(), response.message());
            }
            return mapper.readValue(response.body().string(), new TypeReference<Map<Integer, String>>(){});
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete retrieve action.", e);
        }
    }

    public Map<Integer, Boolean> latest(ParameterStore parameters) throws PactSafeActivityException {
        try {
            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
            Request request = new Request.Builder()
                    .url(baseUrl + "/latest")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() >= 400) {
                throw new PactSafeActivityException("latest", response.code(), response.message());
            }
            return mapper.readValue(response.body().string(), new TypeReference<Map<Integer, Boolean>>(){});
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete latest action.", e);
        }
    }

    public ParameterStore load(ParameterStore parameters) throws PactSafeActivityException {
        try {
            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
            Request request = new Request.Builder()
                    .url(baseUrl + "/load/json")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() >= 400) {
                throw new PactSafeActivityException("load", response.code(), response.message());
            }
            return mapper.readValue(response.body().string(), ParameterStore.class);
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
