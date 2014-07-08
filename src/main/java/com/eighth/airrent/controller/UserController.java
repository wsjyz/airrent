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
    public UserInfo login(@RequestParam String loginName,@RequestParam String password){
        UserInfo userInfo = new UserInfo();
        if(StringUtils.isBlank(loginName) || StringUtils.isBlank(password)){
            userInfo.setLoginStatus("LOGIN_INFO_NULL");
            userInfo.setLoginTip("请输入用户名密码");
        }else{
            userInfo.setLoginStatus("SUCCESS");
            userInfo.setLoginTip("登录成功");
        }
        return userInfo;
    }
    
    

    @RequestMapping(value = "/regist")
    @ResponseBody
	public UserInfo regist(@RequestParam UserInfo userInfo) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

    @RequestMapping(value = "/obtainVerifyCode")
    @ResponseBody
	public VerifyCode obtainVerifyCode() throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

    @RequestMapping(value = "/checkVerifyCode")
    @ResponseBody
	public String checkVerifyCode(@RequestParam String tokenId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

    @RequestMapping(value = "/resetPassword")
    @ResponseBody
	public String resetPassword(@RequestParam String mobile,@RequestParam  String newPassword)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}
    @RequestMapping(value = "/modifyUserInfo")
    @ResponseBody
	public String modifyUserInfo(@RequestParam UserInfo userInfo)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}
}
