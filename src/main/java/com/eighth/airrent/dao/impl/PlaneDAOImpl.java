package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "PlaneDAO")
public class PlaneDAOImpl extends BaseDAO implements PlaneDAO{

	@Override
	public Plane findPlaneById(String planeId) throws RemoteInvokeException {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_plane where plane_id='"+planeId+"'");
		List<Plane> list = getJdbcTemplate().query(sql.toString(), new RowMapper<Plane>() {
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
		});
		if (CollectionUtils.isEmpty(list)) {
			return new Plane();
		}
		return list.get(0);
	}
}
