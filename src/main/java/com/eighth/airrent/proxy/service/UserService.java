package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
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
    @RemoteMethod(methodVarNames={ "loginName","password"})
	UserInfo login(String loginName, String password)
			throws RemoteInvokeException;

	/**
	 * 注册 必填项mobile、loginName、password
	 * 
	 * @param userInfo
	 * @return
	 * @throws RemoteInvokeException
	 */
    @RemoteMethod(methodVarNames={ "userInfo"})
	UserInfo regist(UserInfo userInfo) throws RemoteInvokeException;

	/**
	 * 获取注册验证码
	 * 
	 * @return
	 * @throws RemoteInvokeException
	 */
    @RemoteMethod(methodVarNames={ "mobile"})
	String obtainVerifyCode(String mobile) throws RemoteInvokeException;

    /**
     * 验证注册码是否正确
     * @param code 是obtainRegistCode方法返回的
     * @return RIGHT正确、FAULT错误、PAST过期
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "token"})
    String checkVerifyCode(String token) throws RemoteInvokeException;

	/**
	 * 重置密码
	 * 
	 * @param mobile
	 * @param newPassword
	 *            明文
	 * @return SUCCESS密码重置成功，FAIL重置失败
	 * @throws RemoteInvokeException
	 */
    @RemoteMethod(methodVarNames={ "mobile","newPassword"})
	String resetPassword(String mobile, String newPassword)
			throws RemoteInvokeException;

    String resetPasswordById(String userId, String newPassword);
	/**
	 * 修改个人信息
	 * 
	 * @param userInfo
	 * @return SUCCESS成功，FAIL失败
	 * @throws RemoteInvokeException
	 */
    @RemoteMethod(methodVarNames={ "userInfo"})
	UserInfo modifyUserInfo(UserInfo userInfo) throws RemoteInvokeException;

	/**
	 * 查看个人信息
	 * @param userId
	 * @return
	 * @throws RemoteInvokeException
	 */
    @RemoteMethod(methodVarNames={ "userId"})
	UserInfo getById(String userId) throws RemoteInvokeException;

    public UserInfo findUser(UserInfo userInfo);

    /**
     * 分页查询userInfo
     * @param page
     * @param userInfo
     * @return
     */
    OpenPage findUserByPage(OpenPage page, UserInfo userInfo);

    String updateUserStatus(UserInfo user);

    String deleteUser(String userId);

    String saveUser(UserInfo user);
}
