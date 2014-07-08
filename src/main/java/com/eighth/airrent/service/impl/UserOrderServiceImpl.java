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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String payOrder(String userId, String orderId)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOrder findOrderById(String orderId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrderById(String orderId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOrder addUserOrder(UserOrder order) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

}
