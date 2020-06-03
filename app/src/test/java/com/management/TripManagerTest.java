package com.management;

import com.authentication.Constants;


import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TripManagerTest {
    private long millis = System.currentTimeMillis();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
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
        assertTrue(tripManager.createTrip(startingLocation,destination,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripStartingLocationIsEmpty(){
        assertFalse(tripManager.createTrip("",destination,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripStartingLocationIsNull(){
        assertFalse(tripManager.createTrip(null,destination,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripdestinationIsEmpty(){
        assertFalse(tripManager.createTrip(startingLocation,"",stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripDestinationIsNull(){
        assertFalse(tripManager.createTrip(startingLocation,null,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripStartingTimeIsEmpty(){
        assertFalse(tripManager.createTrip(startingLocation,destination,"",seatLeft,userToken));
    }
    @Test
    public void createTripStartingTimeIsNull(){
        assertFalse(tripManager.createTrip(startingLocation,destination,null,seatLeft,userToken));
    }
    @Test
    public void createTripSeatsLeftIsEmpty(){
        assertFalse(tripManager.createTrip(startingLocation,destination,stringTime,"",userToken));
    }
    @Test
    public void createTripSeatsLeftIsNull(){
        assertFalse(tripManager.createTrip(startingLocation,destination,stringTime,null,userToken));
    }
    @Test
    public void createTripUserTokenIsEmpty(){
        assertFalse(tripManager.createTrip(startingLocation,destination,stringTime,seatLeft,""));
    }
    @Test
    public void createTripUserTokenIsNull(){
        assertFalse(tripManager.createTrip(startingLocation,destination,stringTime,seatLeft,null));
    }

    @Test
    public void getTripFromServerUseCase(){
        tripManager.getTripFromServer(1L);
    }


}