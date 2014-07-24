package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/6/27.
 */
public interface UserService {

	/**
	 * 登录
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws RemoteInvokeException
	 */
	UserInfo login(String loginName, String password)
			throws RemoteInvokeException;

	/**
	 * 注册 必填项mobile、loginName、password
	 * 
	 * @param userInfo
	 * @return
	 * @throws RemoteInvokeException
	 */
	UserInfo regist(UserInfo userInfo) throws RemoteInvokeException;

	/**
	 * 获取注册验证码
	 * 
	 * @return
	 * @throws RemoteInvokeException
	 */
	String obtainVerifyCode() throws RemoteInvokeException;

    /**
     * 验证注册码是否正确
     * @param code 是obtainRegistCode方法返回的
     * @return RIGHT正确、FAULT错误、PAST过期
     * @throws RemoteInvokeException
     */
    String checkVerifyCode(String code) throws RemoteInvokeException;

	/**
	 * 重置密码
	 * 
	 * @param mobile
	 * @param newPassword
	 *            明文
	 * @return SUCCESS密码重置成功，FAIL重置失败
	 * @throws RemoteInvokeException
	 */
	String resetPassword(String mobile, String newPassword)
			throws RemoteInvokeException;

	/**
	 * 修改个人信息
	 * 
	 * @param userInfo
	 * @return SUCCESS成功，FAIL失败
	 * @throws RemoteInvokeException
	 */
	String modifyUserInfo(UserInfo userInfo) throws RemoteInvokeException;

	/**
	 * 查看个人信息
	 * @param userId
	 * @return
	 * @throws RemoteInvokeException
	 */
	UserInfo getById(String userId) throws RemoteInvokeException;
}
