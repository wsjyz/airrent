package com.eighth.airrent.proxy.service;

import java.util.List;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.OrderPayRel;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface OrderPayRelService {
	OrderPayRel findOrderByOrderNum(String orderNum);

	String save(OrderPayRel OrderPayRel);
}
