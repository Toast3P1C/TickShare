package com.network;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.model.ITrip;

import java.util.List;


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
     * Returns a String of values from the server
     * @param uri "http://10.0.2.2:8080/"(for localhost) + extension
     * @return
     */
    public List<ITrip> get(String uri);

    /**
     * Http post
     * posts an object to the desired server
     * @param uri "http://10.0.2.2:8080/"(for localhost) + extension
     * @param o the object you want to write, check if server can handle your object
     * @return returns true if servers sends back 200
     */
    public boolean post(String uri, Object o);

    /**
     * Http put
     * replaces an existing object at the server with the modified one
     * @param url "http://10.0.2.2:8080/"(for localhost) + extension
     * @param o the modified object you want to write, check if server can handle your object
     * @return returns true if servers sends back 200
     */
    public boolean put(String url, Object o);


    /**
     * Deletes the object via its ID
     * @param uri "http://10.0.2.2:8080/"(for localhost) + extension
     * @return returns true if servers sends back 200
     */
    public boolean delete(String uri);
}
