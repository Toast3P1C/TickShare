package com.management;

import com.model.ITrip;

import java.util.List;
import java.util.Map;

public interface ITripManager {
    /**
     * Creates a trip and stores it temporary on the device
     * @param startingLocation String which represents the name of the Station you start
     * @param destination String which represents the name of the final station
     * @param startingTime String which holds the departure time
     * @param seatsLeft String which holds how many seats are left
     * @param userToken unique token for identifying users
     * @return
     */
    public boolean createTripWithUserToken(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken);

    /**
     * Creates a trip for an non logged in user
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @return
     */
    public boolean createTripWithoutUserToken(String startingLocation, String destination, String startingTime, String seatsLeft);

    /**
     * Returns an Trip object provided by the server
     * can be null if the trip does not exist
     * @return
     */
    public List<ITrip> getTripsFromServer(String uri);

    /**
     * Returns an Trip object provided by the server
     * can be null if the trip does not exist
     * @param id
     * @return
     */
    public ITrip getTripFromServer(String uri, long id);
    /**
     * Method for updating an trip. It firstly gets the trip from the server and then updates
     * all fields you want to update
     * @param uri the uri to the server plus extension
     * @param id of the trip you want to update
     * @param startingLocation see above
     * @param destination see above
     * @param startingTime see above
     * @param seatsLeft see above
     * @return is true if everything went well :)
     */
    public boolean updateTrip(String uri, long id,String startingLocation, String destination, String startingTime, String seatsLeft);

    /**
     * Method for deleting an Trip from the server, returns true if the trip is deleted
     * @param id id of the trip
     * @return
     */
    public boolean deleteTrip(long id);

    /**
     * Returns the location and description of an error
     * @return
     */
    public Map<String,String> getErrorMap();

    public boolean sendTripToServer(String uri, ITrip trip);

    public List<ITrip> getTripList();
}
