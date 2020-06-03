package com.network;

import com.authentication.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class NetworkManager implements INetworkManager {

    private static AsyncHttpClient  client = new AsyncHttpClient();

    @Override
    public void get (String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(url, params, responseHandler);
    }
    @Override
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(url,params,responseHandler);
    }
    @Override
    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.put(url,params,responseHandler);
    }

    public void delete(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        client.delete(url,params,responseHandler);
    }


}
