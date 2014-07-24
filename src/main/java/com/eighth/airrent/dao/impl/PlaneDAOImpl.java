package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.util.CommonUtils;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "PlaneDAO")
public class PlaneDAOImpl extends BaseDAO implements PlaneDAO{

	@Override
	public Plane findPlaneById(String planeId) {
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

	@Override
	public String addPlane(Plane plane) {
		StringBuffer sql = new StringBuffer();
		String planeId = CommonUtils.genUUID();
		sql.append("INSERT into t_airrent_plane(plane_id,plane_name,plane_image,plane_no,fly_unit_cost,plane_type,time_in_product,product_area,"
				+ "driving_mile,speed,colour,show_unit_cost,plane_price,product_org,airline_id,airport_id,sit_counts,reminder_sit_counts) values('"
				+ planeId
				+ "','"+plane.getPlaneName()
				+ "','"+plane.getPlaneImage()
				+ "','"+plane.getPlaneNo()
				+ "','"+plane.getFlyUnitCost()
				+ "','"+plane.getPlaneType()
				+ "','"+plane.getTimeInProduct()
				+ "','"+plane.getProductArea()
				+ "','"+plane.getDrivingMile()
				+ "','"+plane.getSpeed()
				+ "','"+plane.getColour()
				+ "','"+plane.getShowUnitCost()
				+ "','"+plane.getPlanePrice()
				+ "','"+plane.getProductOrg()
				+ "','"+plane.getAirlineId()
				+ "','"+plane.getAirportId()
				+ "','"+plane.getSitCounts()
				+ "','"+plane.getReminderSitCounts()
				+ "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@Override
	public String deletePlane(String planeId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_airrent_plane where plane_id='"
				+ planeId + "'");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@Override
	public String updatePlane(Plane plane) {
		StringBuffer sql = new StringBuffer();
		sql.append("update t_airrent_plane set plane_name='"+plane.getPlaneName()
				+ "',plane_image='"+plane.getPlaneImage()
				+ "',plane_no='"+plane.getPlaneNo()
				+ "',fly_unit_cost='"+plane.getFlyUnitCost()
				+"',plane_type='"+plane.getPlaneType()
				+"',time_in_product='"+plane.getTimeInProduct()
				+"',product_area='"+plane.getProductArea()
				+"',driving_mile='"+plane.getDrivingMile()
				+"',speed='"+plane.getSpeed()
				+"',colour='"+plane.getColour()
				+"',show_unit_cost='"+plane.getShowUnitCost()
				+"',plane_price='"+plane.getPlanePrice()
				+"',product_org='"+plane.getProductOrg()
				+"',airline_id='"+plane.getAirlineId()
				+"',airport_id='"+plane.getAirportId()
				+"',sit_counts='"+plane.getSitCounts()
				+"',reminder_sit_counts='"+plane.getReminderSitCounts()
				+"'  where plane_id='"+plane.getPlaneId()+"'");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}
}
