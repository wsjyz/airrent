package com.eighth.airrent.controller;

import com.alipay.util.UtilDate;
import com.eighth.airrent.domain.OrderPayRel;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.ApkVersionService;
import com.eighth.airrent.proxy.service.OrderPayRelService;
import com.eighth.airrent.proxy.service.UserService;
import com.eighth.airrent.util.AirrentUtils;
import com.eighth.airrent.web.FastJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dam on 2014/6/30.
 */
@Controller
@RequestMapping(value = "/UserService")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ApkVersionService apkVersionService;
	@Autowired
	OrderPayRelService orderPayRelService;
	/**
	 * 已测试
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public UserInfo login(@RequestParam String loginName,
			@RequestParam String password) throws RemoteInvokeException {
		return userService.login(loginName, password);
	}

	/**
	 * 已测试
	 * 
	 * @param userInfo
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/regist")
	@ResponseBody
	public UserInfo regist(@FastJson UserInfo userInfo)
			throws RemoteInvokeException {
		return userService.regist(userInfo);
	}

	/**
	 * 已测试
	 * 
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/obtainVerifyCode")
	@ResponseBody
	public String obtainVerifyCode(@RequestParam String mobile) throws RemoteInvokeException {
		return userService.obtainVerifyCode(mobile);
	}

	/**
	 * 已测试
	 * 
	 * @param token
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/checkVerifyCode")
	@ResponseBody
	public String checkVerifyCode(@RequestParam String token)
			throws RemoteInvokeException {
		return userService.checkVerifyCode(token);
	}

	/**
	 * 已测试
	 * 
	 * @param mobile
	 * @param newPassword
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public String resetPassword(@RequestParam String mobile,
			@RequestParam String newPassword) throws RemoteInvokeException {
		return userService.resetPassword(mobile, newPassword);
	}

	/**
	 * 已测试
	 * 
	 * @param userInfo
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/modifyUserInfo")
	@ResponseBody
	public UserInfo modifyUserInfo(@FastJson UserInfo userInfo)
			throws RemoteInvokeException {
		return userService.modifyUserInfo(userInfo);
	}

	@RequestMapping(value = "/getById")
	@ResponseBody
	public UserInfo getById(@RequestParam String userId)
			throws RemoteInvokeException {
		return userService.getById(userId);
	}
	
	
	@RequestMapping("/paymentByUser")
	public ModelAndView paymentByUser(@RequestParam String userId,@RequestParam String card) {
		ModelAndView view = new ModelAndView();
		String orderNo = UtilDate.getOrderNum();
		try {
			OrderPayRel OrderPayRel=new OrderPayRel();
			OrderPayRel.setCard(card);
			OrderPayRel.setUserId(userId);
			OrderPayRel.setOrderNum(orderNo);
			orderPayRelService.save(OrderPayRel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String money="0";
		if(card.equals(AirrentUtils.GENERAL_CARD)){
			money="2500";
		}else if(card.equals(AirrentUtils.DISTINGUISHED_CARD)){
			money="5000";
		}else if(card.equals(AirrentUtils.SILVER_CARD)){
			money="10000";
		}else if(card.equals(AirrentUtils.GOLD_CARD)){
			money="15000";
		}else if(card.equals(AirrentUtils.PLATINUM_CARD)){
			money="20000";
		}else if(card.equals(AirrentUtils.GOLDEN_DIAMOND_CARD)){
			money="30000";
		}
		view.addObject("WIDseller_email", "globalwings.cn@gmail.com");
		view.addObject("WIDout_trade_no", orderNo);
		view.addObject("WIDsubject", orderNo);
		view.addObject("WIDtotal_fee",money );

		view.setViewName("payCard/index");
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
		view.setViewName("payCard/alipayapi");
		return view;
	}

	@RequestMapping("/toNotify")
	public ModelAndView toNotify(@RequestParam String out_trade_no,@RequestParam String  result) {
		ModelAndView view = new ModelAndView();
		view.setViewName("payCard/notify_url");
		return view;
	}

	@RequestMapping("/tocallbackurl")
	public ModelAndView tocallbackurl(@RequestParam String out_trade_no,@RequestParam String  result) {
		ModelAndView view = new ModelAndView();
		view.setViewName("payCard/call_back_url");
		return view;
	}
	
}
