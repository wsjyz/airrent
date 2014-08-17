package com.eighth.airrent.domain;

/**
 * Created by dam on 2014/7/2.
 */
public class Airline extends BaseDomain {

	private String airlineId;
	private String airlineName;
	private String airlineImage;
	private String airportId;

	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}

	public String getAirportId() {
		return airportId;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getAirlineImage() {
		return airlineImage;
	}

	public void setAirlineImage(String airlineImage) {
		this.airlineImage = airlineImage;
	}
}
