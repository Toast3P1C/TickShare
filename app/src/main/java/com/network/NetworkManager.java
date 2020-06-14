package com.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.ITrip;
import com.model.Trip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class NetworkManager implements INetworkManager {

    private final static Logger LOG = LogManager.getLogger(NetworkManager.class);
    private Gson gson;

    public NetworkManager(){
        this.gson = new Gson();
    }

    public List<ITrip> get(String uri) {
       List<ITrip> trips = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        URL url = null;
        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            trips = new ArrayList<>(readStream(in));
        } catch (Exception e) {
            LOG.error("Cant perform Http get: ", e);
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return trips;
    }

    public boolean post(String uri, Object o) {
        boolean success = false;
        HttpURLConnection urlConnection = null;
        URL url = null;
        String jsonString = writeObjectToJsonString(o);

        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            if (setUpRequests("post", urlConnection) != null) {
                urlConnection = setUpRequests("post", urlConnection);
            }

        } catch (Exception e) {
            LOG.error("Can not connect to server: ", e);
        }

        writeToServer(urlConnection, jsonString);

        try {
            if (urlConnection.getResponseCode() == 200) {
                success = true;
            } else {
                LOG.error("Server responded with code: " + urlConnection.getResponseCode());
                success = false;
            }
        } catch (Exception e) {
            LOG.error("Can not get any answer from server", e);
        }
        return success;
    }

    public boolean put(String uri, Object o){
        boolean success = false;
        HttpURLConnection urlConnection = null;
        URL url = null;
        String jsonString = writeObjectToJsonString(o);

        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            if (setUpRequests("put", urlConnection) != null) {
                urlConnection = setUpRequests("put", urlConnection);
            }

        } catch (Exception e) {
            LOG.error("Can not connect to server: ", e);
        }

        writeToServer(urlConnection, jsonString);

        try {
            if (urlConnection.getResponseCode() == 200) {
                success = true;
            } else {
                LOG.error("Server responded with code: " + urlConnection.getResponseCode());
                success = false;
            }
        } catch (Exception e) {
            LOG.error("Can not get any answer from server", e);
        }
        return success;

    }

    public boolean delete(String uri){
        boolean success = false;
        HttpURLConnection urlConnection = null;
        URL url;

        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            if (setUpRequests("delete", urlConnection) != null) {
                urlConnection = setUpRequests("delete", urlConnection);
            }

        } catch (Exception e) {
            LOG.error("Can not connect to server: ", e);
        }

        try {
            urlConnection.connect();
        }catch (Exception e){
            LOG.error("Error while deleting: ",e);
        }

        try {
            if (urlConnection.getResponseCode() == 200) {
                success = true;
            } else {
                LOG.error("Server responded with code: " + urlConnection.getResponseCode());
                success = false;
            }
        } catch (Exception e) {
            LOG.error("Can not get any answer from server", e);
        }
        return success;

    }

    private String writeObjectToJsonString(Object o) {
        return gson.toJson(o);
    }

    private HttpURLConnection setUpRequests(String request, HttpURLConnection urlConnection) {
        switch (request) {
            case "post": {
                try {
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                    urlConnection.setRequestProperty("Accept", "application/json");
                    urlConnection.setDoOutput(true);
                    return urlConnection;
                } catch (Exception e) {
                    LOG.error("Error while setting up post: ", e);
                }

            }
            case "put": {
                try {
                    urlConnection.setRequestMethod("PUT");
                    urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                    urlConnection.setRequestProperty("Accept", "application/json");
                    urlConnection.setDoOutput(true);
                    return urlConnection;
                } catch (Exception e) {
                    LOG.error("Error while setting up put: ", e);
                }
            }
            case "delete": {
                try {
                    urlConnection.setRequestMethod("DELETE");
                    urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                    urlConnection.setRequestProperty("Accept", "application/json");
                    urlConnection.setDoOutput(true);
                    return urlConnection;
                } catch (Exception e) {
                    LOG.error("Error while setting up delete: ", e);
                }
            }
            default: {
                return null;
            }
        }
    }

    private void writeToServer(URLConnection urlConnection, String jsonString) {
        try (OutputStream outputStream = urlConnection.getOutputStream()) {
            byte[] input = jsonString.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        } catch (Exception e) {
            LOG.error("Can not write outputstream: ", e);
        }
    }


    private List<ITrip> readStream(InputStream in) {
        Type tripListType = new TypeToken<ArrayList<Trip>>(){}.getType();
        List<ITrip> trips = null;
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            trips = gson.fromJson(response.toString(),tripListType);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return trips;
    }
}
