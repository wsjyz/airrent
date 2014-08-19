package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirlineDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;

@Repository(value = "AirlineDAO")
public class AirlineDAOImpl extends BaseDAO implements AirlineDAO {

	@Override
	public Airline findAirlineById(String airlineId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_airline where airline_id='"+airlineId+"'");
		List<Airline> list = getJdbcTemplate().query(sql.toString(), new AirlineMapper());
		if (CollectionUtils.isEmpty(list)) {
			return new Airline();
		}
		return list.get(0);
	}
    public class AirlineMapper implements RowMapper<Airline>{
    	@Override
		public Airline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Airline line = new Airline();
			line.setAirlineId(rs.getString("airline_id"));
			line.setAirlineImage(rs.getString("airline_image"));
			line.setAirlineName(rs.getString("airline_name"));
			line.setAirportId(rs.getString("airport_id"));
			return line;
		}
    	
    }
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,
			String airlineId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from t_airrent_plane where airline_id='"+airlineId+"'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if(count>0){
			openPage.setTotal(count);
			sql=new StringBuffer();
			sql.append("select * from t_airrent_plane where airline_id='"+airlineId+"' limit "+openPage.getPageSize()+" OFFSET "+(openPage.getFirst() - 1)+"");
			List<Plane> list = getJdbcTemplate().query(sql.toString(), new PlaneMapper());
			openPage.setRows(list);
		}else{
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Plane>());

		}
		return openPage;
		
	}
	 public class PlaneMapper implements RowMapper<Plane>{
		 @Override
			public Plane mapRow(ResultSet rs, int rowNum) throws SQLException {
				Plane plane = new Plane();
				plane.setAirlineId(rs.getString("airline_id"));
				plane.setAirportId(rs.getString("airport_id"));
				plane.setColour(rs.getString("colour"));
				plane.setDrivingMile(rs.getBigDecimal("driving_mile"));
				plane.setFlyUnitCost(rs.getBigDecimal("fly_unit_cost"));
				plane.setPlaneId(rs.getString("plane_id"));
				plane.setPlaneImage(rs.getString("plane_image"));
				plane.setPlaneName(rs.getString("plane_name"));
				plane.setPlaneNo(rs.getString("plane_no"));
				plane.setPlanePrice(rs.getBigDecimal("plane_price"));
				plane.setPlaneType(rs.getString("plane_type"));
				plane.setProductArea(rs.getString("product_area"));
				plane.setProductOrg(rs.getString("product_org"));
				plane.setReminderSitCounts(rs.getInt("reminder_sit_counts"));
				plane.setShowUnitCost(rs.getBigDecimal("show_unit_cost"));
				plane.setSitCounts(rs.getInt("sit_counts"));
				plane.setSpeed(rs.getBigDecimal("speed"));
				plane.setTimeInProduct(rs.getString("time_in_product"));
				return plane;
			}
	    	
	    }
	@Override
	public List<Airline> findAirlineAllById(String airportId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_airline where airport_id='"+airportId+"'");
		List<Airline> list = getJdbcTemplate().query(sql.toString(), new AirlineMapper());
		
		return list;
	}
}
