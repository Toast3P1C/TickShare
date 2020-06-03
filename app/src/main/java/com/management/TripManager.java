package com.management;

import com.authentication.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.model.ITrip;
import com.model.Trip;
import com.network.INetworkManager;
import com.network.NetworkManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class TripManager implements ITripManager {
    private final static Logger LOG = LogManager.getLogger(TripManager.class);
    private List<ITrip> tripList = new ArrayList<>();
    private Map<String, String> errorMap = new HashMap<>();

    private INetworkManager networkManager;


    public TripManager() {
        this.networkManager = new NetworkManager();
    }

    public boolean createTrip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        if (checkTripValues(startingLocation, destination, startingTime, seatsLeft, userToken)) {
            ITrip trip = new Trip(startingLocation, destination, startingTime, seatsLeft, userToken);
            if (tripList.add(trip)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public ITrip getTripFromServer(long id) {
        final ITrip[] trip = {null};
        networkManager.get("trip/" + id, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    System.out.println(objectMapper.readValue(response.toString(), Trip.class));
                    trip[0] = objectMapper.readValue(response.toString(), Trip.class);
                } catch (JsonProcessingException e) {
                    LOG.error("Error parsing Json", e);
                }
            }
        });
        return trip[0];
    }

    @Override
    public boolean updateTrip(long id, String startingLocation, String destination, String startingTime, String seatsLeft) {
        final boolean success[] = {false};
        ITrip trip = setNewTripValues(id, startingLocation, destination, startingTime, seatsLeft);
        if (trip != null) {
            networkManager.put("trip/" + id, setParamsForRequest(trip), new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    if (statusCode >= 200 && statusCode <= 300) {
                        success[0] = true;
                    } else {
                        success[0] = false;
                        LOG.error("error while updating trip, statuscode: ", statusCode);
                    }
                }
            });
            return success[0];
        } else {
            return false;
        }
    }
//TODO: Method stub
    @Override
    public boolean deleteTrip(long id) {
        return false;
    }

    private ITrip setNewTripValues(long id, String startingLocation, String destination, String startingTime, String seatsLeft) {
        Trip trip = (Trip) getTripFromServer(id);
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

    private RequestParams setParamsForRequest(ITrip trip) {
        RequestParams params = new RequestParams();
        params.add("startingLocation", trip.getStartingLocation());
        params.add("destination", trip.getDestination());
        params.add("startingTime", trip.getStartingTime());
        params.add("seatsLeft", trip.getSeatsLeft());
        params.add("userToken", trip.getUserToken());
        params.setUseJsonStreamer(true);
        return params;
    }


    public boolean sendTripToServer(ITrip trip) {
        final boolean[] success = {false};
        networkManager.post("/trip", setParamsForRequest(trip), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode >= 200 && statusCode <= 300) {
                    success[0] = true;
                } else {
                    success[0] = false;
                    LOG.error("error while posting trip, statuscode = ", statusCode);
                }

            }
        });
        return success[0];
    }


    private boolean checkTripValues(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        if(startingTime == null || startingTime.isEmpty()){
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(),"Starting Time is null or empty! ");
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
        if ( destination == null ||destination.isEmpty() ) {
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
        if (userToken == null || userToken.isEmpty()) {
            LOG.error("User Token is null or empty");
            return false;
        }
        return true;
    }

}
