package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.util.CommonUtils;

/**
 * Created by dam on 14-6-25.
 */
@Repository(value = "AirportDAO")
public class AirportDAOImpl extends BaseDAO implements AirportDAO {

	@SuppressWarnings("deprecation")
	@Override
	public OpenPage<Airport> findAirportList(OpenPage<Airport> openPage,
			String airportName) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_airrent_airport where airport_name like '%"
				+ airportName + "%'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if (count > 0) {
			openPage.setTotal(count);
			sql = new StringBuffer();
			sql.append("select * from t_airrent_airport where airport_name like '%"
					+ airportName
					+ "%' limit "
					+ openPage.getPageSize()
					+ " OFFSET " + (openPage.getFirst() - 1) + "");
			List<Airport> list = getJdbcTemplate().query(sql.toString(),
					new RowMapper<Airport>() {
						@Override
						public Airport mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Airport airport = new Airport();
							airport.setAirportId(rs.getString("airport_id"));
							airport.setAirportImage(rs
									.getString("airport_name"));
							airport.setAirportName(rs
									.getString("airport_image"));
							airport.setDescription(rs.getString("description"));
							return airport;
						}
					});
			openPage.setRows(list);
		} else {
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Airport>());

		}
		return openPage;
	}

	@Override
	public Airport findAirportById(String airportId) {
		StringBuffer sql = new StringBuffer(
				"select * from t_airrent_airport where airport_id='"
						+ airportId + "' ");

		List<Airport> list = getJdbcTemplate().query(sql.toString(),
				new RowMapper<Airport>() {
					@Override
					public Airport mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Airport airport = new Airport();
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

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public OpenPage<Plane> findPlaneByAirportId(OpenPage openPage,
			String airportId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_airrent_plane where airport_id='"
				+ airportId + "'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if (count > 0) {
			openPage.setTotal(count);
			sql = new StringBuffer();
			sql.append("select * from t_airrent_plane where airport_id='"
					+ airportId + "' limit " + openPage.getPageSize()
					+ " OFFSET " + (openPage.getFirst() - 1) + "");
			List<Plane> list = getJdbcTemplate().query(sql.toString(),
					new RowMapper<Plane>() {
						@Override
						public Plane mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Plane plane = new Plane();
							plane.setPlaneId(rs.getString("plane_id"));
							plane.setPlaneName(rs.getString("plane_name"));
							plane.setPlaneImage(rs.getString("plane_image"));
							plane.setPlaneNo(rs.getString("plane_no"));
							plane.setFlyUnitCost(rs
									.getBigDecimal("fly_unit_cost"));
							plane.setPlaneType(rs.getString("plane_type"));
							plane.setTimeInProduct(rs
									.getString("time_in_product"));
							plane.setProductArea(rs.getString("product_area"));
							plane.setDrivingMile(rs
									.getBigDecimal("driving_mile"));
							plane.setSpeed(rs.getBigDecimal("speed"));
							plane.setColour(rs.getString("colour"));
							plane.setShowUnitCost(rs
									.getBigDecimal("show_unit_cost"));
							plane.setPlanePrice(rs.getBigDecimal("plane_price"));
							plane.setProductOrg(rs.getString("product_org"));
							plane.setAirlineId(rs.getString("airline_id"));
							plane.setAirportId(rs.getString("airport_id"));
							plane.setSitCounts(rs.getInt("sit_counts"));
							plane.setReminderSitCounts(rs
									.getInt("reminder_sit_counts"));
							return plane;
						}
					});
			openPage.setRows(list);
		} else {
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Plane>());

		}
		return openPage;
	}

	@Override
	public String addAirport(Airport airport) {
		StringBuffer sql = new StringBuffer();
		String airportId = CommonUtils.genUUID();
		sql.append("INSERT into t_airrent_airport(airport_id,airport_name,description,airport_image) values('"
				+ airportId
				+ "','"
				+ airport.getAirportName()
				+ "','"
				+ airport.getDescription()
				+ "','"
				+ airport.getAirportImage()
				+ "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@Override
	public String deleteAirprot(String airprotId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_airrent_airport where airport_id='"
				+ airprotId + "'");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

}
