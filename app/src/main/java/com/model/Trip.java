package com.model;

import java.util.Date;

public class Trip implements ITrip {

    private String startingLocation;
    private String destination;
    private Date startingTime;
    private Integer seatsLeft;
    private String userToken;

    public Trip(String startingLocation, String destination, Date startingTime, Integer seatsLeft, String userToken) {
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
        this.userToken = userToken;
    }

    @Override
    public String getStartingLocation() {
        return startingLocation;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public Date getStartingTime() {
        return startingTime;
    }

    @Override
    public Integer getSeatsLeft() {
        return seatsLeft;
    }

    @Override
    public String getUserToken() {
        return userToken;
    }

}
