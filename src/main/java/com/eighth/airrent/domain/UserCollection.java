package com.eighth.airrent.domain;

/**
 * Created by dam on 14-6-25.
 */
public class UserCollection {

    private String userId;
    private String airportId;
    private String planeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }
}
