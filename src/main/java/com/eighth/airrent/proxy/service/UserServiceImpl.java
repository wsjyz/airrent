package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.UserInfo;

/**
 * Created by dam on 2014/6/27.
 */
public class UserServiceImpl implements UserService {
    @Override
    public UserInfo login(String loginName, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginStatus("LOCAL");
        return userInfo;
    }
}
