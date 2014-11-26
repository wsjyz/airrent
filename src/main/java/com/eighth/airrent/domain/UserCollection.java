package com.eighth.airrent.domain;

import java.math.BigDecimal;

/**
 * Created by dam on 14-6-25.
 */
public class UserCollection extends BaseDomain {

	private String userId;
	private String airlineId;
	private String planeId;

	private String planeName;
	private String airlineName;

	private Integer planCount;
	private String planePicUrl;
	private String airlinePicUrl;
	private String userCount;
	private BigDecimal money;
	private int clickCount;

	public String getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getClickCount() {
		return clickCount;
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

	public String getPlaneId() {
		return planeId;
	}

	public void setPlaneId(String planeId) {
		this.planeId = planeId;
	}

	public Integer getPlanCount() {
		return planCount;
	}

	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}

	public String getPlanePicUrl() {
		return planePicUrl;
	}

	public void setPlanePicUrl(String planePicUrl) {
		this.planePicUrl = planePicUrl;
	}

	public String getAirlinePicUrl() {
		return airlinePicUrl;
	}

	public void setAirlinePicUrl(String airlinePicUrl) {
		this.airlinePicUrl = airlinePicUrl;
	}
}
