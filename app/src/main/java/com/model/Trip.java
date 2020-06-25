package com.model;

import com.authentication.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * Erstellung der der Trip Objekte in der App ohne dass ein User eingeloggt sein muss
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     */
    public Trip(String startingLocation, String destination, String startingTime, String seatsLeft){
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
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

    @Override
    public Date getStartingTimeAsDate() {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            date = simpleDateFormat.parse(startingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public int getSeatsLeftAsInteger() {
        return Integer.valueOf(seatsLeft);
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

    @Override
    public void setSeatsLeft(String seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    @Override
    public String toString(){
        return "Id: "+id +", Starting location: "+startingLocation+", Destination: "+destination+
                ", Starting Time: "+startingTime+", Seats left: "+seatsLeft+", userToken: "+userToken;
    }
}
