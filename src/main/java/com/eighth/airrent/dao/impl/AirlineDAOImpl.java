package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirlineDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;

@Repository(value = "AirlineDAO")
public class AirlineDAOImpl extends BaseDAO implements AirlineDAO {

	@Override
	public Airline findAirlineById(String airlineId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_airline where airline_id='"+airlineId+"'");
		List<Airline> list = getJdbcTemplate().query(sql.toString(), new RowMapper<Airline>() {
			@Override
			public Airline mapRow(ResultSet rs, int rowNum) throws SQLException {
				Airline line = new Airline();
				line.setAirlineId(rs.getString("airline_id"));
				line.setAirlineImage(rs.getString("airline_image"));
				line.setAirlineName(rs.getString("airline_name"));
				return line;
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return new Airline();
		}
		return list.get(0);
	}
	public OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,
			String airlineId) {
		return null;
		
	}
}
