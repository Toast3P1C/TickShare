package com.network;

import com.authentication.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class NetworkManager implements INetworkManager {

    private static AsyncHttpClient  client = new AsyncHttpClient();

    @Override
    public void get (String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }
    @Override
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url),params,responseHandler);
    }
    @Override
    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.put(getAbsoluteUrl(url),params,responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.BASE_URL + relativeUrl;
    }
}
