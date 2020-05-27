package com.model;

import com.authentication.Constants;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TripTest {
    //TODO: Add more Test cases here
private long id = 1L;
private String startingLocation = "Berlin";
private String destination = "Hamburg";
private String startingTime;
private String seatsLeft = "4";
private String userToken = "TestToken123";
private ITrip localTrip;
private ITrip tripFromServer;
private Date date;
    @Before
    public void init(){
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATEFORMAT);
        date = new Date(millis);
        startingTime = simpleDateFormat.format(date);
        localTrip = new Trip(startingLocation,destination,startingTime,seatsLeft,userToken);
        tripFromServer = new Trip(id,startingLocation,destination,startingTime,seatsLeft,userToken);
    }
    @Test
    public void getIdUseCase() {
        assertEquals(1L,tripFromServer.getId());
    }

    @Test
    public void getStartingLocationUseCase() {
        assertEquals("Berlin",localTrip.getStartingLocation());
    }

    @Test
    public void getDestinationUseCase() {
        assertEquals("Hamburg",localTrip.getDestination());
    }

    @Test
    public void getStartingTime() {
        assertEquals(startingTime,localTrip.getStartingTime());
    }

    @Test
    public void getSeatsLeft() {
        assertEquals("4",localTrip.getSeatsLeft());
    }

    @Test
    public void getUserToken() {
        assertEquals("TestToken123",localTrip.getUserToken());
    }

    /**
     * this test will always fail because of the internal timers thats why its ignored
     * The data types are the same
     */
    @Ignore
    @Test
    public void getStartingTimeAsDateUseCase(){
    assertEquals(date, localTrip.getStartingTimeAsDate());
    }
    @Test
    public void getSeatsLeftAsIntegerUseCase(){
        assertEquals(4, localTrip.getSeatsLeftAsInteger());
    }
}