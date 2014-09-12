package com.eighth.airrent.dao.impl;


import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.SystemDAO;
import com.eighth.airrent.domain.APKVersion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dam on 2014/7/30.
 */
@Repository("SystemDAO")
public class SystemDAOImpl extends BaseDAO implements SystemDAO {



    @Override
    public APKVersion findLastVersion() {
        StringBuilder sql = new StringBuilder("");
        sql.append("select * from t_apk_version");
        List<APKVersion> versionList = getJdbcTemplate().query(sql.toString(),new APKVersionRowMapper());
        if(!CollectionUtils.isEmpty(versionList)){
            APKVersion version = versionList.get(0);
            return version;
        }
        return null;
    }

    public class APKVersionRowMapper implements RowMapper<APKVersion>{

        @Override
        public APKVersion mapRow(ResultSet rs, int rowNum) throws SQLException {
            APKVersion version = new APKVersion();
            version.setDownloadUrl(rs.getString("download_url"));
            version.setLastVersionCode(rs.getString("last_version_code"));
            return version;
        }
    }


}
