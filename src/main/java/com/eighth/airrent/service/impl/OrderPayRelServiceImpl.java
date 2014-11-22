package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.OrderPayRelDAO;
import com.eighth.airrent.domain.OrderPayRel;
import com.eighth.airrent.proxy.service.OrderPayRelService;

@Service("OrderPayRelService")
public class OrderPayRelServiceImpl implements OrderPayRelService {

	@Autowired
	OrderPayRelDAO orderPayRelDao;
	@Override
	public OrderPayRel findOrderByOrderNum(String orderNum) {
		return orderPayRelDao.findOrderByOrderNum(orderNum);
	}

	@Override
	public String save(OrderPayRel OrderPayRel) {
		return orderPayRelDao.save(OrderPayRel);
	}
}
