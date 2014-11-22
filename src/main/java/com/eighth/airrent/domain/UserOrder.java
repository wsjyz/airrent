package com.eighth.airrent.domain;

import java.math.BigDecimal;

/**
 * Created by dam on 14-6-25.
 */
public class UserOrder extends BaseDomain{
    private String orderId;//订单编号
    private String userId;//提交人ID
    private String airportId;
    //用途:商公务包机CHARTER|私人直升机PRIVATE_COPTER|航摄航拍AERIAL|农地森FARM|商业活动COMERCIAL|观光试飞TOUR|婚礼地产活动WEDDING_ESTATE|其它OTHER
    private String orderUse;
    private String startDate;//出发日期
    private String endDate;//返程日期
    private BigDecimal stayHour;//停留时间
    private BigDecimal flyHour;//飞行时间
    private String starting;//出发地
    private String destination;//目的地
    private int userCounts;//人数
    private String optTime;//产生时间
    private BigDecimal downPayment;//定金
    private int orderCounts;//预定数量
    //支付状态 ONLINE_PAYED线上已支付 OFFLINE_PAYED线下已支付 NOT_PAY_ONLINE 线上未支付  NOT_PAY_OFFLINE线下未支付
    private String orderStatus;
    private String description;//备注

    private String orderLetter;
    private String orderNumber;
    private String planeId;
    private String loginName;
    private String planeName;

    private UserInfo userInfo;
    private Plane plane;
    private Airport airport;
    private Airline airline;

    public BigDecimal getStayHour() {
        return stayHour;
    }

    public void setStayHour(BigDecimal stayHour) {
        this.stayHour = stayHour;
    }

    public BigDecimal getFlyHour() {
        return flyHour;
    }

    public void setFlyHour(BigDecimal flyHour) {
        this.flyHour = flyHour;
    }

    public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getOrderUse() {
		return orderUse;
	}
	public void setOrderUse(String orderUse) {
		this.orderUse = orderUse;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getUserCounts() {
		return userCounts;
	}
	public void setUserCounts(int userCounts) {
		this.userCounts = userCounts;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public BigDecimal getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}
	public int getOrderCounts() {
		return orderCounts;
	}
	public void setOrderCounts(int orderCounts) {
		this.orderCounts = orderCounts;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

    public String getOrderLetter() {
        return orderLetter;
    }

    public void setOrderLetter(String orderLetter) {
        this.orderLetter = orderLetter;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }


}
