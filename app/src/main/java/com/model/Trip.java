package com.model;

import java.util.Date;

public class Trip implements ITrip {

    private long id;
    private String startingLocation;
    private String destination;
    private String startingTime;
    private String seatsLeft;
    private String userToken;


    /**
     * Wird benötigt um die Objekte von Server zu bekommen
     * @param id
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @param userToken
     */
    public Trip(long id, String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        this.id = id;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
        this.userToken = userToken;
    }

    /**
     * Erstellung der der Trip Objekte in der App
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @param userToken
     */
    public Trip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken){
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
        this.userToken = userToken;
    }

    public long getId() {
        return id;
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
    public String getStartingTime() {
        return startingTime;
    }

    @Override
    public String getSeatsLeft() {
        return seatsLeft;
    }

    @Override
    public String getUserToken() {
        return userToken;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public void setSeatsLeft(String seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    @Override
    public String toString(){
        return "Id: "+id +", Starting location: "+startingLocation+", Destination: "+destination+
                ", Starting Time: "+startingTime+", Seats left: "+seatsLeft+", userToken: "+userToken;
    }
}
