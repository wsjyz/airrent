package com.eighth.airrent.dao.impl;

import org.springframework.stereotype.Repository;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserOrderDAO;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "UserOrderDAO")
public class UserOrderDAOImpl extends BaseDAO implements UserOrderDAO{

	@Override
	public OpenPage<UserOrder> findUserOrder(OpenPage openPage, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String payOrder(String userId, String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOrder findOrderById(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrderById(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

   
}
