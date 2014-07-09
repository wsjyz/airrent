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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo regist(UserInfo userInfo) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerifyCode obtainVerifyCode() throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkVerifyCode(String tokenId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPassword(String mobile, String newPassword)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modifyUserInfo(UserInfo userInfo)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}
}
