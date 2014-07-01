package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/6/27.
 */
public interface UserService {

    public UserInfo login(String loginName,String password) throws RemoteInvokeException;
}
