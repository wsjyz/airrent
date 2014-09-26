package com.eighth.airrent.controller;

import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.ApkVersionService;
import com.eighth.airrent.proxy.service.UserService;
import com.eighth.airrent.web.FastJson;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String obtainVerifyCode() throws RemoteInvokeException {
		return userService.obtainVerifyCode();
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
	
}
