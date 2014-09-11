package com.eighth.airrent.proxy.service;


import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/4.
 */
public interface SystemService {
    /**
     * 更新apk
     * @param currentVersionCode 当前客户端的版本
     * @return
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "currentVersionCode" })
    APKVersion updateAPK(String currentVersionCode)throws RemoteInvokeException;





}
