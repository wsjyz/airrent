package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.SettingDao;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.domain.Setting;
import com.eighth.airrent.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kkk on 14/9/12.
 */
@Repository(value = "SettingDAO")
public class SettingDaoImpl extends BaseDAO implements SettingDao {

    @Override
    public Setting loadSetting() {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from t_airrent_setting limit 1");
        List<Setting> list = getJdbcTemplate().query(sql.toString(), new SettingMapper());
        if (CollectionUtils.isEmpty(list)) {
            return new Setting();
        }
        return list.get(0);
    }

    @Override
    public String saveSetting(Setting setting) {
        StringBuffer sql = new StringBuffer();
        Object[] params = new Object[7];
        if(StringUtils.isBlank(setting.getSetttingId())) {
            String settingId = CommonUtils.genUUID();
            sql.append("insert into t_airrent_setting(setting_id,index_message,fee_info,content," +
                    "cycle,time,choice) " +
                    "values(?,?,?,?,?,?,?)");
            params[0] = settingId;
            params[1] = setting.getIndexMessage();
            params[2] = setting.getFeeInfo();
            params[3] = setting.getContent();
            params[4] = setting.getCycle();
            params[5] = setting.getTime();
            params[6] = setting.getChoice();
        }else{
            sql.append("update t_airrent_setting set index_message=?,fee_info=?," +
                    "content=?,cycle=?,time=?,choice=? " +
                    "where setting_id=?");
            params[0] = setting.getIndexMessage();
            params[1] = setting.getFeeInfo();
            params[2] = setting.getContent();
            params[3] = setting.getCycle();
            params[4] = setting.getTime();
            params[5] = setting.getChoice();
            params[6] = setting.getSetttingId();

        }
        int update = getJdbcTemplate().update(sql.toString(), params);
        if (update > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    public class SettingMapper implements RowMapper<Setting>{

        @Override
        public Setting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Setting setting=new Setting();
            setting.setSetttingId(rs.getString("setting_id"));
            setting.setIndexMessage(rs.getString("index_message"));
            setting.setChoice(rs.getString("choice"));
            setting.setContent(rs.getString("content"));
            setting.setFeeInfo(rs.getString("fee_info"));
            setting.setCycle(rs.getString("cycle"));
            setting.setTime(rs.getString("time"));
            return setting;
        }
    }
}
