package com.management;

import com.authentication.Constants;


import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TripManagerTest {
    private long millis = System.currentTimeMillis();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATEFORMAT);
    private Date date;
    private ITripManager tripManager;
    private String startingLocation = "Berlin";
    private String destination = "Hamburg";
    private String stringTime;
    private String seatLeft = "3";
    private String userToken = "testToken1234";

    @Before
    public void init(){
    tripManager = new TripManager();
    date = new Date(millis);
    stringTime = simpleDateFormat.format(date);
    }
    @Test
    public void createTripUseCase(){
        //assertTrue(tripManager.createTrip(startingLocation,destination,stringTime,seatLeft,userToken));
    }



}