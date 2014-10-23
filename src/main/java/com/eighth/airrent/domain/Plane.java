package com.eighth.airrent.domain;

import java.math.BigDecimal;

/**
 * Created by dam on 14-6-25.
 */
public class Plane extends BaseDomain{
    private String planeId;
    private String planeName;//飞机名称
    private String planeImage;//飞机图片
    private String planeImageName;//飞机图片文件名
    private String planeNo;//飞机编号
    private BigDecimal unitCost;//单价
    private BigDecimal flyUnitCost;//飞行单价
    private String planeType;//飞机类型
    private String timeInProduct;//生产年代
    private String productArea;//产地
    private BigDecimal drivingMile;//续航里程
    private BigDecimal speed;//巡航速度
    private String colour;//颜色
    private BigDecimal showUnitCost;//展示单价
    private BigDecimal planePrice;//飞机价格
    private String productOrg;//生产商
    private String airlineId;//所属航空公司
    private String airportId;//所属机场
    private String sitCounts;//总座位数
    private int reminderSitCounts;//剩余座位数
    private String status;//状态

    private String airlineName;//隶属公司名称

    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getPlaneImage() {
        return planeImage;
    }

    public void setPlaneImage(String planeImage) {
        this.planeImage = planeImage;
    }

    public String getPlaneImageName() {
        return planeImageName;
    }

    public void setPlaneImageName(String planeImageName) {
        this.planeImageName = planeImageName;
    }

    public String getPlaneNo() {
        return planeNo;
    }

    public void setPlaneNo(String planeNo) {
        this.planeNo = planeNo;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getFlyUnitCost() {
        return flyUnitCost;
    }

    public void setFlyUnitCost(BigDecimal flyUnitCost) {
        this.flyUnitCost = flyUnitCost;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public String getTimeInProduct() {
        return timeInProduct;
    }

    public void setTimeInProduct(String timeInProduct) {
        this.timeInProduct = timeInProduct;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public BigDecimal getDrivingMile() {
        return drivingMile;
    }

    public void setDrivingMile(BigDecimal drivingMile) {
        this.drivingMile = drivingMile;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public BigDecimal getShowUnitCost() {
        return showUnitCost;
    }

    public void setShowUnitCost(BigDecimal showUnitCost) {
        this.showUnitCost = showUnitCost;
    }

    public BigDecimal getPlanePrice() {
        return planePrice;
    }

    public void setPlanePrice(BigDecimal planePrice) {
        this.planePrice = planePrice;
    }

    public String getProductOrg() {
        return productOrg;
    }

    public void setProductOrg(String productOrg) {
        this.productOrg = productOrg;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getSitCounts() {
        return sitCounts;
    }

    public void setSitCounts(String sitCounts) {
        this.sitCounts = sitCounts;
    }

    public int getReminderSitCounts() {
        return reminderSitCounts;
    }

    public void setReminderSitCounts(int reminderSitCounts) {
        this.reminderSitCounts = reminderSitCounts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
