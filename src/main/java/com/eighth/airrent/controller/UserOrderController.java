package com.eighth.airrent.controller;

import org.eclipse.jetty.http.HttpTester.Request;
import org.eclipse.jetty.server.Authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserOrderService;
import com.eighth.airrent.proxy.service.UserService;
import com.eighth.airrent.web.FastJson;

/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/UserOrderService")
public class UserOrderController {

	@Autowired
	UserOrderService userOrderService;

	@ResponseBody
	@RequestMapping(value = "/addUserOrder")
	public UserOrder saveUserOrder(@FastJson UserOrder order) {
		UserOrder userOrder = null;
		try {
			userOrder = userOrderService.addUserOrder(order);
		} catch (RemoteInvokeException e) {
			e.printStackTrace();
		}
		return userOrder;
	}
	/**
	 * 已测试
	 * @param openPage
	 * @param userId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findUserOrder")
	@ResponseBody
	public OpenPage<UserOrder> findUserOrder(@FastJson OpenPage openPage,
			@RequestParam String userId) throws RemoteInvokeException {
		return userOrderService.findUserOrder(openPage, userId);
	}

	/**
	 * 已测试
	 * @param orderId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findOrderById")
	@ResponseBody
	public UserOrder findOrderById(@RequestParam String orderId)
			throws RemoteInvokeException {
		return userOrderService.findOrderById(orderId);
	}

	/**
	 * 已测试
	 * @param orderId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/deleteOrderById")
	@ResponseBody
	public String deleteOrderById(@RequestParam String orderId)
			throws RemoteInvokeException {
		return userOrderService.deleteOrderById(orderId);
	}
	@RequestMapping("/payment")
	public ModelAndView toPay(@RequestParam String orderId) {
		ModelAndView view = new ModelAndView();
		UserOrder userOrder = null;
		try {
			userOrder = userOrderService.findOrderById(orderId);
		} catch (RemoteInvokeException e) {
			e.printStackTrace();
		}
		String orderNo = userOrder.getOrderNumber();
		view.addObject("WIDseller_email", "globalwings.cn@gmail.com");
		view.addObject("WIDout_trade_no", orderNo);
		view.addObject("WIDsubject", orderNo);
		view.addObject("WIDtotal_fee", userOrder.getDownPayment());

		view.setViewName("payOrder/index");
		return view;
	}

	@RequestMapping("/toAlipayapi")
	public ModelAndView toAlipayapi(@RequestParam String WIDseller_email,
			@RequestParam String WIDout_trade_no,
			@RequestParam String WIDsubject, @RequestParam String WIDtotal_fee) {
		ModelAndView view = new ModelAndView();
		view.addObject("WIDseller_email", WIDseller_email);
		view.addObject("WIDout_trade_no", WIDout_trade_no);
		view.addObject("WIDsubject", WIDsubject);
		view.addObject("WIDtotal_fee", WIDtotal_fee);
		view.setViewName("payOrder/alipayapi");
		return view;
	}

	@RequestMapping("/toNotify")
	public ModelAndView toNotify(@RequestParam String out_trade_no,@RequestParam String  result) {
		ModelAndView view = new ModelAndView();
		view.setViewName("payOrder/notify_url");
		return view;
	}

	@RequestMapping("/tocallbackurl")
	public ModelAndView tocallbackurl(@RequestParam String out_trade_no,@RequestParam String  result) {
		ModelAndView view = new ModelAndView();
		view.setViewName("payOrder/call_back_url");
		return view;
	}

}
