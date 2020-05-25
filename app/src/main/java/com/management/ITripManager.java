package com.management;

import com.model.ITrip;

public interface ITripManager {
    /**
     * Creates a trip and sends it directly to the RESTful Webservice
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @param userToken
     * @return
     */
    public boolean createTrip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken);

    /**
     * Sends id to the Server and return an Trip object
     * @param id
     * @return
     */
    public ITrip getTripFromServer(long id);

    /**
     * Method for updating an trip. It Firstly gets The trip from the server and then updates all Fields you want to update
     * @param id
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @return
     */
    public boolean updateTrip(long id,String startingLocation, String destination, String startingTime, String seatsLeft);
}
