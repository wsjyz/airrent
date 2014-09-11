package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.Setting;

/**
 * Created by kkk on 14/9/12.
 */
public interface SettingService {

    Setting loadSetting();

    String saveSetting(Setting setting);
}
