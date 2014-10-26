package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface UserOrderService {

    /**
     * 订单列表
     * @param openPage 分页信息json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号 pageSize每页显示条数
     * @param userId
     * @return
     */
    @RemoteMethod(methodVarNames={ "openPage","userId"})
    OpenPage<UserOrder> findUserOrder(OpenPage openPage,String userId)throws RemoteInvokeException;

    /**
     * 新增订单
     * @return
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "order"})
    UserOrder addUserOrder(UserOrder order)throws RemoteInvokeException;

    /**
     * 支付订单
     * @param userId
     * @param orderId 订单编号
     * @return
     * @throws RemoteInvokeException
     */
    String payOrder(String userId,String orderId)throws Exception;

    /**
     * 获取订单信息
     * @param orderId
     * @return
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "orderId"})
    UserOrder findOrderById(String orderId)throws RemoteInvokeException;

    /**
     *
     * 删除订单
     * @param orderId
     * @return SUCCESS成功，FAIL失败
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "orderId"})
    String deleteOrderById(String orderId)throws RemoteInvokeException;

    OpenPage findUserOrders(OpenPage page, UserOrder userOrder);

	UserOrder findOrderByOrderNo(String orderNo);

	void updateOrderByOrderNo(String orderNo, String string);

	String updateOrderStatus(String orderId, String orderStatus);

}
