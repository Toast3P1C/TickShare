package com.network;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public interface INetworkManager {
    /**
     * Http get Request
     * @param url just the extension for : "http://10.0.2.2:8080/" I.e. /trips gets u all trips stored at the server
     * @param params can be null
     * @param responseHandler
     */
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    /**
     * Http post request
     * @param url same as get
     * @param params
     * @param responseHandler
     */
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    /**
     * Http put
     * @param url same as post :P
     * @param params
     * @param responseHandler
     */
    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);
}
