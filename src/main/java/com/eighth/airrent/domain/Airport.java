package com.eighth.airrent.domain;

/**
 * Created by dam on 14-6-25.
 */
public class Airport extends BaseDomain {

	private String airportId;
	private String airportName;// 机场名称
	private String description;// 机场描述
	private String airportImage;// 机场图片
	private String lat;// 经度
	private String lng;// 纬度

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAirportId() {
		return airportId;
	}

	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAirportImage() {
		return airportImage;
	}

	public void setAirportImage(String airportImage) {
		this.airportImage = airportImage;
	}
}
