package com.management;

import com.authentication.Constants;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.model.ITrip;
import com.model.Trip;
import com.network.INetworkManager;
import com.network.NetworkManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;


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
    @Ignore
    public void getTripFromServerUseCase(){
        ITrip trip = new Trip(startingLocation,destination,stringTime,seatLeft,userToken);
        INetworkManager networkManager = mock(NetworkManager.class);
//      when(networkManager.get(Constants.BASE_URL+"/trips/1",null, ArgumentMatchers.<>any())).thenReturn(trip);


    }




}