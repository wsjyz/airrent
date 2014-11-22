package com.eighth.airrent.dao;


import com.eighth.airrent.domain.OrderPayRel;

public interface OrderPayRelDAO {
	
	OrderPayRel findOrderByOrderNum(String orderNum);

	String save(OrderPayRel OrderPayRel);

}
