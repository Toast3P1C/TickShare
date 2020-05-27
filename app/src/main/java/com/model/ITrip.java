package com.model;

import java.util.Date;

public interface ITrip {
    /**
     * Returns the Trip id which is generate by the backend so note that
     * you dont have to private one when creating a new trip
     * @return
     */
    public long getId();
    /**
     * Returns the name of the location where the ticket provider starts i.e Berlin- Grunewald
     * @return
     */
    public String getStartingLocation();

    /**
     * Returns the name of the destination of the ticket provider
     * @return
     */
    public String getDestination();

    /**
     * Returns at which time the ticket provider starts its trip
     * note: The time is provided as a String because of the way its delivered to the server
     * if you need the time as Date use method below
     * @return
     */
    public String getStartingTime();

    /**
     * Returns how many seats the provider has left
     * note: The time is provided as a String because of the way its delivered to the server
     * if you need it as Integer please use method below
     * @return
     */
    public String getSeatsLeft();

    /**
     * returns the usertoken which is unique for every user
     * @return
     */
    public String getUserToken();

    /**
     * returns the starting time as Date object
     * @return
     */
    public Date getStartingTimeAsDate();

    /**
     * returns the amount of seats left as integer
     * @return
     */
    public int getSeatsLeftAsInteger();


}
