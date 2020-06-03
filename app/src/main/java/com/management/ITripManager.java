package com.management;

import com.model.ITrip;

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
    public boolean createTrip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken);

    /**
     * Returns an Trip object provided by the server
     * can be null if the trip does not exist
     * @param id
     * @return
     */
    public ITrip getTripFromServer(long id);

    /**
     * Method for updating an trip. It firstly gets the trip from the server and then updates
     * all fields you want to update
     * @param id of the trip you want to update
     * @param startingLocation see above
     * @param destination see above
     * @param startingTime see above
     * @param seatsLeft see above
     * @return is true if everything went well :)
     */
    public boolean updateTrip(long id,String startingLocation, String destination, String startingTime, String seatsLeft);

    public boolean deleteTrip(long id);
}
