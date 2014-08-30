package com.eighth.airrent.controller;

import org.eclipse.jetty.http.HttpTester.Request;
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
    public ModelAndView toPay() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }
	
	@RequestMapping("/toAlipayapi")
    public ModelAndView toAlipayapi() {
        ModelAndView view = new ModelAndView();
        view.setViewName("alipayapi");
        return view;
    }
	@RequestMapping("/toNotify")
    public ModelAndView toNotify() {
        ModelAndView view = new ModelAndView();
        view.setViewName("notify_url");
        return view;
    }
	
	@RequestMapping("/tocallbackurl")
    public ModelAndView tocallbackurl() {
        ModelAndView view = new ModelAndView();
        view.setViewName("call_back_url");
        return view;
    }
}
