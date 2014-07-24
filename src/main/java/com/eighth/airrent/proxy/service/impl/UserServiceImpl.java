package com.eighth.airrent.proxy.service.impl;

import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserService;

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

    @Override
    public UserInfo regist(UserInfo userInfo) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String obtainVerifyCode() throws RemoteInvokeException {
        return null;
    }

    @Override
    public String checkVerifyCode(String tokenId) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String resetPassword(String mobile, String newPassword) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String modifyUserInfo(UserInfo userInfo) throws RemoteInvokeException {
        return null;
    }

	@Override
	public UserInfo getById(String userId) throws RemoteInvokeException {
		return null;
	}
}
