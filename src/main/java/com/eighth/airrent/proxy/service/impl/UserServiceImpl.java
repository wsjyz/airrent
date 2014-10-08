package com.eighth.airrent.proxy.service.impl;

import com.eighth.airrent.domain.OpenPage;
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
        return null;
    }

    @Override
    public UserInfo regist(UserInfo userInfo) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String obtainVerifyCode(String mobile) throws RemoteInvokeException {
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
    public String resetPasswordById(String userId, String newPassword) {
        return null;
    }

    @Override
    public UserInfo modifyUserInfo(UserInfo userInfo) throws RemoteInvokeException {
        return null;
    }

	@Override
	public UserInfo getById(String userId) throws RemoteInvokeException {
		return null;
	}

    public UserInfo findUser(UserInfo userInfo) {
        return null;
    }

    @Override
    public OpenPage findUserByPage(OpenPage page, UserInfo userInfo) {
        return null;
    }

    @Override
    public String updateUserStatus(UserInfo user) {
        return null;
    }

    @Override
    public String deleteUser(String userId) {
        return null;
    }

    @Override
    public String saveUser(UserInfo user) {
        return null;
    }
}
