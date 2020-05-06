package com.management;

import com.model.ITrip;
import com.model.Trip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripManager {
    private List<ITrip> tripList = new ArrayList<>();
    private final static Logger LOG = LogManager.getLogger(TripManager.class);
    private Map<String,String> errorMap = new HashMap<>();


    public boolean createTrip(String startingLocation, String destination, Date startingTime, Integer seatsLeft, String userToken) {
        if(checkTripValues(startingLocation,destination,startingTime,seatsLeft,userToken)){
            //ITrip trip = new Trip(startingLocation,destination,startingTime,seatsLeft,userToken);
            //return tripList.add(trip);
        }
        return false;
    }

    private boolean checkTripValues(String startingLocation, String destination, Date startingTime, Integer seatsLeft, String userToken) {
        if (startingLocation.isEmpty() || startingLocation == null) {
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"starting Location can not be empty");
            LOG.error("starting Location is empty or null");
            return false;
        }
        if(destination.isEmpty() || destination == null){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Destination can not be empty");
            LOG.error("starting Location is empty or null");
            return false;
        }
        if(startingTime == null || startingTime.before(new Date())){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Date cant be empty or in the past");
            LOG.error("Date is null or in the past");
            return false;
        }
        if(seatsLeft == null || seatsLeft < 0 ){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Seats must be a value >= 0");
            LOG.error("Seats left is null or smaller then zero");
        }
        if(userToken.isEmpty() || userToken == null){
            LOG.error("User Token is null or empty");
            return false;
        }
        return true;
    }

}
