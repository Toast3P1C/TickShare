package com.model;

import com.authentication.Constants;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TripTest {
private long id = 1L;
private String startingLocation = "Berlin";
private String destination = "Hamburg";
private String startingTime;
private String seatsLeft = "4";
private String userToken = "TestToken123";
private ITrip localTrip;
private ITrip tripFromServer;
private Trip nonInterfacetrip;
private Date date;
    @Before
    public void init(){
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        date = new Date(millis);
        startingTime = simpleDateFormat.format(date);
        localTrip = new Trip(startingLocation,destination,startingTime,seatsLeft,userToken);
        tripFromServer = new Trip(id,startingLocation,destination,startingTime,seatsLeft,userToken);
        nonInterfacetrip = new Trip(id,startingLocation,destination,startingTime,seatsLeft,userToken);
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
    public void getStartingLocationEmpty() {
        nonInterfacetrip.setStartingLocation("");
        assertEquals("",nonInterfacetrip.getStartingLocation());
    }
    @Test
    public void getStartingLocationNull() {
        nonInterfacetrip.setStartingLocation(null);
        assertEquals(null,nonInterfacetrip.getStartingLocation());
    }


    @Test
    public void getDestinationUseCase() {
        assertEquals("Hamburg",localTrip.getDestination());
    }

    @Test
    public void getDestinationEmpty() {
        nonInterfacetrip.setDestination("");
        assertEquals("",nonInterfacetrip.getDestination());
    }

    @Test
    public void getDestinationNull() {
        nonInterfacetrip.setDestination(null);
        assertEquals(null,nonInterfacetrip.getDestination());
    }
    @Test
    public void getStartingTimeUseCase() {
        assertEquals(startingTime,localTrip.getStartingTime());
    }

    @Test
    public void getStartingTimeEmpty() {
        nonInterfacetrip.setStartingTime("");
        assertEquals("",nonInterfacetrip.getStartingTime());
    }

    @Test
    public void getStartingTimeNull() {
        nonInterfacetrip.setStartingTime(null);
        assertEquals(null,nonInterfacetrip.getStartingTime());
    }

    @Test
    public void getSeatsLeftUseCase() {
        assertEquals("4",localTrip.getSeatsLeft());
    }
    @Test
    public void getSeatsLeftEmpty() {
        nonInterfacetrip.setSeatsLeft("");
        assertEquals("",nonInterfacetrip.getSeatsLeft());
    }
    @Test
    public void getSeatsLeftNull() {
        nonInterfacetrip.setSeatsLeft(null);
        assertEquals(null,nonInterfacetrip.getSeatsLeft());
    }

    @Test
    public void getUserTokenUseCase() {
        assertEquals("TestToken123",localTrip.getUserToken());
    }


    /**
     * this test will always fail because of the internal timers
     * The data types are the same
     */
    @Test
    public void getStartingTimeAsDateUseCase(){
    assertEquals(date, localTrip.getStartingTimeAsDate());
    }
    @Test
    public void getSeatsLeftAsIntegerUseCase(){
        assertEquals(4, localTrip.getSeatsLeftAsInteger());
    }

}