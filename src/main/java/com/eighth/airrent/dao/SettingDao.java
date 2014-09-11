package com.eighth.airrent.dao;

import com.eighth.airrent.domain.Setting;

/**
 * Created by kkk on 14/9/12.
 */
public interface SettingDao {

    Setting loadSetting();

    String saveSetting(Setting setting);
}
