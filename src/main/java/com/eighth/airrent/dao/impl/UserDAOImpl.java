package com.eighth.airrent.dao.impl;

import org.springframework.stereotype.Repository;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserDAO;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/6/27.
 */
@Repository(value = "UserDAO")
public class UserDAOImpl  extends BaseDAO implements UserDAO {

	@Override
	public UserInfo login(String loginName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo regist(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerifyCode obtainVerifyCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkVerifyCode(String tokenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPassword(String mobile, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modifyUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

  
}
