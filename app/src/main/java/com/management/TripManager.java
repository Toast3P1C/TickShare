package com.management;

import com.authentication.Constants;
import com.loopj.android.http.RequestParams;
import com.model.ITrip;
import com.model.Trip;
import com.network.INetworkManager;
import com.network.NetworkManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripManager implements ITripManager {
    private final static Logger LOG = LogManager.getLogger(TripManager.class);
    private List<ITrip> tripList = new ArrayList<>();
    private Map<String, String> errorMap = new HashMap<>();

    private INetworkManager networkManager;


    public TripManager() {
        this.networkManager = new NetworkManager();
    }

    public boolean createTripWithUserToken(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        if (checkTripValues(startingLocation, destination, startingTime, seatsLeft)) {
            ITrip trip = new Trip(startingLocation, destination, startingTime, seatsLeft, userToken);
            return tripList.add(trip);
        } else {
            LOG.error("Could not add Trip ");
            return false;
        }
    }

    @Override
    public boolean createTripWithoutUserToken(String startingLocation, String destination, String startingTime, String seatsLeft) {
        if (checkTripValues(startingLocation, destination, startingTime, seatsLeft)) {
            ITrip trip = new Trip(startingLocation, destination, startingTime, seatsLeft);
            return tripList.add(trip);
        } else {
            LOG.error("Could not add Trip ");
            return false;
        }
    }

    @Override
    public List<ITrip> getTripsFromServer(String uri) {
        List<ITrip> response = networkManager.get(uri);
        return response;

    }

    @Override
    public ITrip getTripFromServer(String uri, long id) {
        List<ITrip> response = networkManager.get(uri + id);
        return response.get(0);
    }


    @Override
    public boolean updateTrip(String uri, long id, String startingLocation, String destination, String startingTime, String seatsLeft) {
        networkManager.put(uri+"/trip"+id,setNewTripValues(id,startingLocation,destination,startingTime,seatsLeft));
        return false;
    }

    @Override
    public boolean deleteTrip(long id) {
        return false;
    }


    private ITrip setNewTripValues(long id, String startingLocation, String destination, String startingTime, String seatsLeft) {
        List<ITrip> tripList = networkManager.get(Constants.BASE_URL+"/trip/"+id);
        Trip trip = (Trip) tripList.get(0);
        if (trip != null) {
            if (!startingLocation.isEmpty() && startingLocation != null) {
                trip.setStartingLocation(startingLocation);
            }

            if (!destination.isEmpty() && destination != null) {
                trip.setDestination(destination);
            }

            if (!startingTime.isEmpty() && startingTime != null) {
                trip.setStartingTime(startingTime);
            }

            if (!seatsLeft.isEmpty() && seatsLeft != null) {
                trip.setSeatsLeft(seatsLeft);
            }
        }
        return trip;
    }


    private boolean checkTripValues(String startingLocation, String destination, String startingTime, String seatsLeft) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        if (startingTime == null || startingTime.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Starting Time is null or empty! ");
            LOG.error("Time is empty or null");
            return false;
        }
        try {
            date = simpleDateFormat.parse(startingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startingLocation == null || startingLocation.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "starting Location can not be empty");
            LOG.error("starting Location is empty or null");
            return false;
        }
        if (destination == null || destination.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Destination can not be empty");
            LOG.error("starting Location is empty or null");
            return false;
        }
        if (date == null || startingTime.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Date cant be empty or in the past");
            LOG.error("Date is null or in the past");
            return false;
        }
        if (seatsLeft == null || seatsLeft.isEmpty() || Integer.parseInt(seatsLeft) < 0) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Seats must be a value >= 0");
            LOG.error("Seats left is null or smaller then zero");
            return false;
        }
        return true;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    @Override
    public boolean sendTripToServer(String uri, ITrip trip) {
        return networkManager.post(uri,trip);
    }

    public List<ITrip> getTripList() {
        return tripList;
    }

}
