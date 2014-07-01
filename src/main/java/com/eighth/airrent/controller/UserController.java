package com.eighth.airrent.controller;

import com.eighth.airrent.domain.UserInfo;
import org.apache.commons.lang3.StringUtils;
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
}
