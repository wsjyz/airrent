package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.Setting;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by kkk on 14/9/12.
 */
public interface SettingService {
    @RemoteMethod(methodVarNames={})
    Setting loadSetting() throws RemoteInvokeException;
    @RemoteMethod(methodVarNames={"setting"})
    String saveSetting(Setting setting) throws RemoteInvokeException;
}
