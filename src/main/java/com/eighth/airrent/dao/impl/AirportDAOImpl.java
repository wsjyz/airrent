package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;

/**
 * Created by dam on 14-6-25.
 */
@Repository(value = "AirportDAO")
public class AirportDAOImpl extends BaseDAO implements AirportDAO {

	@Override
	public OpenPage<Airport> findAirportList(String airportName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airport findAirportById(String airportId) {
		StringBuffer sql=new StringBuffer("select * from t_airrent_airport where airport_id='"+airportId+"' ");
		
	List<Airport> list=getJdbcTemplate().query(sql.toString(), new RowMapper<Airport>(){
			@Override
			public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
				Airport airport=new Airport();
				airport.setAirportId(rs.getString("airport_id"));
				airport.setAirportImage(rs.getString("airport_name"));
				airport.setAirportName(rs.getString("description"));
				airport.setDescription(rs.getString("airport_image"));
				return airport;
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return new Airport();
		}
		return list.get(0);
	}

	@Override
	public OpenPage<Plane> findPlaneByAirportId(OpenPage openPage,
			String airportId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
