package com.eighth.airrent.service.impl;

import com.eighth.airrent.dao.SettingDao;
import com.eighth.airrent.domain.Setting;
import com.eighth.airrent.proxy.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kkk on 14/9/12.
 */
@Service("SettingService")
public class SettingServiceImpl implements SettingService {


    @Autowired
    SettingDao settingDao;

    @Override
    public Setting loadSetting() {

        return settingDao.loadSetting();
    }

    @Override
    public String saveSetting(Setting setting) {

        return settingDao.saveSetting(setting);
    }
}
