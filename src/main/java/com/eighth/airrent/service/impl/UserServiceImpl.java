package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.UserDAO;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserService;

/**
 * Created by dam on 2014/6/27.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public UserInfo login(String loginName, String password)
			throws RemoteInvokeException {
		return userDAO.login(loginName, password);
	}

	@Override
	public UserInfo regist(UserInfo userInfo) throws RemoteInvokeException {
		return userDAO.regist(userInfo);
	}

	@Override
	public String obtainVerifyCode() throws RemoteInvokeException {
		return userDAO.obtainVerifyCode();
	}

	@Override
	public String checkVerifyCode(String code) throws RemoteInvokeException {
		return userDAO.checkVerifyCode(code);
	}

	@Override
	public String resetPassword(String mobile, String newPassword)
			throws RemoteInvokeException {
		return userDAO.resetPassword(mobile, newPassword);
	}

	@Override
	public UserInfo modifyUserInfo(UserInfo userInfo)
			throws RemoteInvokeException {
		return userDAO.modifyUserInfo(userInfo);
	}

	@Override
	public UserInfo getById(String userId) throws RemoteInvokeException {
		return userDAO.getById(userId);
	}
}
