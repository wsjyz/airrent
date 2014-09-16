package com.eighth.airrent.domain;

/**
 * Created by dam on 14-6-25.
 */
public class UserCollection extends BaseDomain {

	private String userId;
	private String airportId;
	private String planeId;

	private String planeName;
	private String airportName;

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportName() {
		return airportName;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

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
