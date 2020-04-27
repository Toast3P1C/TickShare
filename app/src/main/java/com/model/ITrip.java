package com.model;

import java.util.Date;

public interface ITrip {
    public String getStartingLocation();
    public String getDestination();
    public Date getStartingTime();
    public Integer getSeatsLeft();
    public String getUserToken();

}
