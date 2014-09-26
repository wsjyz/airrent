package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eighth.airrent.domain.Airport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirlineDAO;
import com.eighth.airrent.dao.ApkVersionDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.util.CommonUtils;

@Repository(value = "ApkVersionDAO")
public class ApkVersionDAOImpl extends BaseDAO implements ApkVersionDAO {

	@Override
	public APKVersion getApkVersion() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_apk_version  order by last_version_code desc ");
		List<APKVersion> list = getJdbcTemplate().query(sql.toString(),new RowMapper<APKVersion>(){
	    	@Override
			public APKVersion mapRow(ResultSet rs, int rowNum)
					throws SQLException {
	    		APKVersion APKVersion = new APKVersion();
	    		APKVersion.setLastVersionCode(rs.getString("last_version_code"));
	    		APKVersion.setDownloadUrl(rs.getString("download_url"));
				return APKVersion;
			}
	    });
		if (CollectionUtils.isEmpty(list)) {
			return new APKVersion();
		}else{
			return list.get(0);

		}
	}


}
