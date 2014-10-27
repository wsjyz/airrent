package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.UserDAO;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.SmsSendService;
import com.eighth.airrent.proxy.service.UserService;

/**
 * Created by dam on 2014/6/27.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	SmsSendService SmsSendService;

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
	public String obtainVerifyCode(String mobile) throws RemoteInvokeException {
		String token= userDAO.obtainVerifyCode();
		try {
			SmsSendService.sendSms(mobile, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
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
    public String resetPasswordById(String userId, String newPassword) {
        return userDAO.resetPasswordById(userId,newPassword);
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

    @Override
    public UserInfo findUser(UserInfo userInfo) {
        return userDAO.find(userInfo);
    }

    @Override
    public OpenPage findUserByPage(OpenPage page, UserInfo userInfo) {
        return userDAO.findUserByPage(page,userInfo);
    }

    @Override
    public String updateUserStatus(UserInfo user) {

        return userDAO.updateUserStatus(user);
    }

    @Override
    public String deleteUser(String userId) {

        return userDAO.deleteUser(userId);
    }

    @Override
    public String saveUser(UserInfo user) {

        return userDAO.saveUser(user);
    }
}
