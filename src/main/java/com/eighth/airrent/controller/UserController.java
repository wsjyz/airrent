package com.eighth.airrent.controller;

import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserService;

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

    @RequestMapping(value = "/login")
    @ResponseBody
    public UserInfo login(@RequestParam String loginName,@RequestParam String password) throws RemoteInvokeException{
        return userService.login(loginName, password);
    }
    
    

    @RequestMapping(value = "/regist")
    @ResponseBody
	public UserInfo regist(@RequestParam UserInfo userInfo) throws RemoteInvokeException {
		return userService.regist(userInfo);
	}

    @RequestMapping(value = "/obtainVerifyCode")
    @ResponseBody
	public String obtainVerifyCode() throws RemoteInvokeException {
		return userService.obtainVerifyCode();
	}

    @RequestMapping(value = "/checkVerifyCode")
    @ResponseBody
	public String checkVerifyCode(@RequestParam String tokenId) throws RemoteInvokeException {
		return userService.checkVerifyCode(tokenId);
	}

    @RequestMapping(value = "/resetPassword")
    @ResponseBody
	public String resetPassword(@RequestParam String mobile,@RequestParam  String newPassword)
			throws RemoteInvokeException {
		return userService.resetPassword(mobile, newPassword);
	}
    @RequestMapping(value = "/modifyUserInfo")
    @ResponseBody
	public String modifyUserInfo(@RequestParam UserInfo userInfo)
			throws RemoteInvokeException {
		return userService.modifyUserInfo(userInfo);
	}
}
