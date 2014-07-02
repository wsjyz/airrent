package com.eighth.airrent.domain;

import java.math.BigDecimal;

/**
 * Created by dam on 14-6-25.
 */
public class UserOrder {
    private String orderId;//订单编号
    private String userId;//提交人ID
    private String airportId;
    //用途:商公务包机CHARTER|私人直升机PRIVATE_COPTER|航摄航拍AERIAL|农地森FARM|商业活动COMERCIAL|观光试飞TOUR|婚礼地产活动WEDDING_ESTATE|其它OTHER
    private String orderUse;
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String starting;//出发地
    private String destination;//目的地
    private int userCounts;//人数
    private String optTime;//产生时间
    private BigDecimal downPayment;//定金
    private int orderCounts;//预定数量
    //支付状态 ONLINE_PAYED线上已支付 OFFLINE_PAYED线下已支付 NOT_PAY未支付
    private String orderStatus;
    private String description;//备注

}
