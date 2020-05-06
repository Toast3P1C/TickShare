package com.model;

import java.util.Date;

public class Trip implements ITrip {

    private long id;
    private String startingLocation;
    private String destination;
    private Date startingTime;
    private Integer seatsLeft;
    private String userToken;

    public Trip(){}
    public Trip(long id, String startingLocation, String destination, Date startingTime, Integer seatsLeft, String userToken) {
        this.id = id;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
        this.userToken = userToken;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString(){
        return "Id: "+id +", Starting location: "+startingLocation+", Destination: "+destination+
                ", Starting Time: "+startingTime+", Seats left: "+seatsLeft+", userToken: "+userToken;
    }
}
