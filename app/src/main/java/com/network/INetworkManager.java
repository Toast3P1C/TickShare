package com.network;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public interface INetworkManager {
/**
 * Available url extensions
 * @GetMapping("/trip/{id}") --> get specific trip by id
 * @GetMapping("/trips") --> get all stored trips
 * @PostMapping("/trip") --> post a new trip
 * @PutMapping("/trip/{id}") --> update a trip
 * @DeleteMapping("trip/{id}") --> deletes a trip
 */


    /**
     * Http get Request
     * note that the params must be provided as string because of the way the "AsyncHttpClient" api works
     * @param url  "http://10.0.2.2:8080/" for localhost
     * @param params can be null
     * @param responseHandler
     */
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    /**
     * Http post request
     * note that the params must be provided as string because of the way the "AsyncHttpClient" api works
     * @param url see available extensions
     * @param params
     * @param responseHandler
     */
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    /**
     * Http put
     * note that the params must be provided as string because of the way the "AsyncHttpClient" api works
     * @param url see available extensions
     * @param params
     * @param responseHandler
     */
    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    /**
     * Http delete
     * @param url see available extensions
     * @param params can be null
     * @param responseHandler
     */
    public void delete(String url,RequestParams params,AsyncHttpResponseHandler responseHandler);
}
