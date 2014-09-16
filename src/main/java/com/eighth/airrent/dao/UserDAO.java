package com.eighth.airrent.dao;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/6/27.
 */
public interface UserDAO {

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     * @throws RemoteInvokeException
     */
    UserInfo login(String loginName,String password) ;

    /**
     * 注册
     * 必填项mobile、loginName、password
     * @param userInfo
     * @return
     * @throws RemoteInvokeException
     */
    UserInfo regist(UserInfo userInfo);

    /**
     * 获取注册验证码
     * @return SUCCESS 成功|FAIL失败
     * @throws RemoteInvokeException
     */
    String obtainVerifyCode();

    /**
     * 验证注册码是否正确
     * @param  code是obtainRegistCode方法返回的
//     * @return RIGHT正确、FAULT错误、PAST过期
     * @throws RemoteInvokeException
     */
    String checkVerifyCode(String code);

    /**
     * 重置密码
     * @param mobile
     * @param newPassword 明文
     * @return SUCCESS密码重置成功，FAIL重置失败
     * @
     */
    String resetPassword(String mobile,String newPassword);

    /**
     * 修改个人信息
     * @param userInfo
     * @
     */
    UserInfo modifyUserInfo(UserInfo userInfo);

	UserInfo getById(String userId);

    /**
     * 查找userinfo
     * @param userInfo
     * @return
     */
    UserInfo find(UserInfo userInfo);

    /**
     * 分页查询
     * @param page
     * @param userInfo
     * @return
     */
    OpenPage findUserByPage(OpenPage page, UserInfo userInfo);

    String updateUserStatus(UserInfo user);

    String deleteUser(String userId);

    String saveUser(UserInfo user);
}
