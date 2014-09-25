package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.InformationDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.OpenPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by dam on 2014/6/29.
 */
@Repository(value = "InformationDAO")
public class InformationDAOImpl extends BaseDAO implements InformationDAO{

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public OpenPage<Information> getInformations(OpenPage openPage) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from t_airrent_information ");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if(count>0){
			openPage.setTotal(count);
			sql=new StringBuffer();
			sql.append("select * from t_airrent_information  order by opt_time desc limit "+openPage.getPageSize()+" OFFSET "+(openPage.getFirst() - 1)+"");
			List<Information> list = getJdbcTemplate().query(sql.toString(), new InformationMapper());
			openPage.setRows(list);
		}else{
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Information>());

		}
		return openPage;
	}

	 public class InformationMapper implements RowMapper<Information>{
		 @Override
			public Information mapRow(ResultSet rs, int rowNum) throws SQLException {
				Information information=new Information();
				information.setInformationId(rs.getString("information_id"));
				information.setTitle(rs.getString("title"));
				information.setContent(rs.getString("content"));
				information.setPostTime(rs.getString("post_time"));
				information.setFileUril(rs.getString("file_uril"));
				information.setStatus(rs.getString("status"));
				return information;
			}
	    }
}
