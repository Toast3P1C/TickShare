package com.network;

import android.content.Context;

import com.authentication.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.model.ITrip;
import com.model.Trip;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

import cz.msebera.android.httpclient.Header;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


public class NetworkManagerTest {


    @Test
    public void get() {
    MockWebServer server = new MockWebServer();
        final CountDownLatch signal = new CountDownLatch(1);
        File file = new File("/Users/paul/StudioProjects/TickShare/app/src/test/java/com/network/test.json");
        InputStream jsonStream = null;
        try {
            jsonStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] jsonBytes = null;
        try {
             jsonBytes = IOUtils.toByteArray(jsonStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.enqueue(new MockResponse().setBody(new String()));
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpUrl httpUrl = server.url(Constants.BASE_URL+"/trips");
        NetworkManager networkManager = new NetworkManager();
        final ITrip[] trip = {null};
        networkManager.get(Constants.BASE_URL+httpUrl,null,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    System.out.println(objectMapper.readValue(response.toString(), Trip.class));
                    trip[0] = objectMapper.readValue(response.toString(), Trip.class);
                } catch (JsonProcessingException e) {

                }
            }
            @Override
            public void onFinish(){
                signal.countDown();
            }
        });

    }

    @Test
    public void post() {
    }

    @Test
    public void put() {
    }
}