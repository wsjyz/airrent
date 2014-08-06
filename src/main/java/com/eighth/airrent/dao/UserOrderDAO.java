package com.eighth.airrent.dao;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface UserOrderDAO {

	/**
	 * 订单列表
	 * 
	 * @param openPage
	 *            分页信息json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号
	 *            pageSize每页显示条数
	 * @param userId
	 * @return
	 */
	OpenPage<UserOrder> findUserOrder(OpenPage openPage, String userId);

	/**
	 * 支付订单
	 * 
	 * @param userId
	 * @param orderId
	 *            订单编号
	 * @return
	 * @throws RemoteInvokeException
	 */
	String payOrder(String userId, String orderId) throws Exception;

	/**
	 * 获取订单信息
	 * 
	 * @param orderId
	 * @return
	 * @throws RemoteInvokeException
	 */
	UserOrder findOrderById(String orderId);

	/**
	 *
	 * 删除订单
	 * 
	 * @param orderId
	 * @return SUCCESS成功，FAIL失败
	 * @throws RemoteInvokeException
	 */
	String deleteOrderById(String orderId);

	UserOrder addUserOrder(UserOrder order);
}
