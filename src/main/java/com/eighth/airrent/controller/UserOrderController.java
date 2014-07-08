package com.eighth.airrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserOrderService;

/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/UserOrderService")
public class UserOrderController {

	@Autowired
	UserOrderService userOrderService;

	@RequestMapping(value = "/findUserOrder")
	@ResponseBody
	public OpenPage<UserOrder> findUserOrder(@RequestParam OpenPage openPage,
			@RequestParam String userId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/payOrder")
	@ResponseBody
	public String payOrder(@RequestParam String userId,
			@RequestParam String orderId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/findOrderById")
	@ResponseBody
	public UserOrder findOrderById(@RequestParam String orderId)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/deleteOrderById")
	@ResponseBody
	public String deleteOrderById(@RequestParam String orderId)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

}
