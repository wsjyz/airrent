package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.UserOrderDAO;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserOrderService;

/**
 * Created by dam on 2014/7/2.
 */
@Service("UserOrderService")
public class UserOrderServiceImpl implements UserOrderService {

	@Autowired
	UserOrderDAO userOrderDAO;
	@Override
	public OpenPage<UserOrder> findUserOrder(OpenPage openPage, String userId)
			throws RemoteInvokeException {
		return userOrderDAO.findUserOrder(openPage, userId);
	}

	@Override
	public String payOrder(String userId, String orderId)
			throws Exception {
		return userOrderDAO.payOrder(userId, orderId);
	}

	@Override
	public UserOrder findOrderById(String orderId) throws RemoteInvokeException {
		return userOrderDAO.findOrderById(orderId);
	}

	@Override
	public String deleteOrderById(String orderId) throws RemoteInvokeException {
		return userOrderDAO.deleteOrderById(orderId);
	}

    @Override
    public OpenPage findUserOrders(OpenPage page, UserOrder userOrder) {

        return userOrderDAO.findUserOrders(page,userOrder);
    }

    @Override
	public UserOrder addUserOrder(UserOrder order) throws RemoteInvokeException {
		return userOrderDAO.addUserOrder(order);
	}

	@Override
	public UserOrder findOrderByOrderNo(String orderNo) {
		return userOrderDAO.findOrderByOrderNo(orderNo);
	}

	@Override
	public void updateOrderByOrderNo(String orderNo, String type) {
		userOrderDAO.updateOrderByOrderNo( orderNo,  type);
	}

}
