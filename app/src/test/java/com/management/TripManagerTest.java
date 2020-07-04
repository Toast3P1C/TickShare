package com.management;

import com.authentication.Constants;
import com.model.ITrip;
import com.model.Trip;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

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
        assertTrue(tripManager.createTripWithUserToken(startingLocation,destination,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripStartingLocationIsEmpty(){
        assertFalse(tripManager.createTripWithUserToken("",destination,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripStartingLocationIsNull(){
        assertFalse(tripManager.createTripWithUserToken(null,destination,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripdestinationIsEmpty(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,"",stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripDestinationIsNull(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,null,stringTime,seatLeft,userToken));
    }
    @Test
    public void createTripStartingTimeIsEmpty(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,destination,"",seatLeft,userToken));
    }
    @Test
    public void createTripStartingTimeIsNull(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,destination,null,seatLeft,userToken));
    }
    @Test
    public void createTripSeatsLeftIsEmpty(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,destination,stringTime,"",userToken));
    }
    @Test
    public void createTripSeatsLeftIsNull(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,destination,stringTime,null,userToken));
    }
    @Test
    public void createTripUserTokenIsEmpty(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,destination,stringTime,seatLeft,""));
    }
    @Test
    public void createTripUserTokenIsNull(){
        assertFalse(tripManager.createTripWithUserToken(startingLocation,destination,stringTime,seatLeft,null));
    }

    @Test
    @Ignore
    public void getTripFromServerUseCase() throws IOException {
        ITrip trip = new Trip(startingLocation,destination,stringTime,seatLeft,userToken);
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("hello, world!"));
        HttpUrl httpUrl = mockWebServer.url("localhost:8080/trips/");
        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody("{\"id\":1,\"startingLocation\":\"Test\",\"destination\":\"test\",\"startingTime\":\"Tue Jun 30 09:49:32 UTC 2020\",\"seatsLeft\":4,\"userToken\":\"Token\"}");


    }




}