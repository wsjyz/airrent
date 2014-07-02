package com.eighth.airrent.domain;

import java.math.BigDecimal;

/**
 * Created by dam on 14-6-25.
 */
public class Plane {
    private String planeId;
    private String planeName;//飞机名称
    private String planeImage;//飞机图片
    private String planeNo;//飞机编号
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
    private int sitCounts;//总座位数
    private int reminderSitCounts;//剩余座位数
}
