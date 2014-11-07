package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eighth.airrent.domain.OpenPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Plane;
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
		List<Plane> list = getJdbcTemplate().query(sql.toString(), new PlaneMapper());
		if (CollectionUtils.isEmpty(list)) {
			return new Plane();
		}
		return list.get(0);
	}
	 public class PlaneMapper implements RowMapper<Plane>{
		 @Override
			public Plane mapRow(ResultSet rs, int rowNum) throws SQLException {
				Plane plane = new Plane();
				plane.setAirlineId(rs.getString("airline_id"));
				plane.setAirportId(rs.getString("airport_id"));
				plane.setColour(rs.getString("colour"));
				plane.setDrivingMile(rs.getBigDecimal("driving_mile"));
				plane.setUnitCost(rs.getBigDecimal("unit_cost"));
				plane.setFlyUnitCost(rs.getBigDecimal("fly_unit_cost"));
				plane.setPlaneId(rs.getString("plane_id"));
				plane.setPlaneImage(rs.getString("plane_image"));
				plane.setPlaneImageName(rs.getString("plane_image_name"));
				plane.setPlaneName(rs.getString("plane_name"));
				plane.setPlaneNo(rs.getString("plane_no"));
				plane.setPlanePrice(rs.getBigDecimal("plane_price"));
				plane.setPlaneType(rs.getString("plane_type"));
				plane.setProductArea(rs.getString("product_area"));
				plane.setProductOrg(rs.getString("product_org"));
				plane.setReminderSitCounts(rs.getInt("reminder_sit_counts"));
				plane.setShowUnitCost(rs.getBigDecimal("show_unit_cost"));
				plane.setSitCounts(rs.getString("sit_counts"));
				plane.setSpeed(rs.getBigDecimal("speed"));
				plane.setTimeInProduct(rs.getString("time_in_product"));
				plane.setStatus(rs.getString("status"));
                 if (rs.getMetaData().getColumnCount() > 21) {
                    plane.setAirlineName(rs.getString("airline_name"));
                 }
             return plane;
			}
	    	
	    }
	@Override
	public String addPlane(Plane plane) {
		StringBuffer sql = new StringBuffer();
		String planeId = CommonUtils.genUUID();
		sql.append("INSERT into t_airrent_plane(plane_id,plane_name,plane_image,plane_no,unit_cost,fly_unit_cost,plane_type,time_in_product,product_area,"
				+ "driving_mile,speed,colour,show_unit_cost,plane_price,product_org,airline_id,airport_id,sit_counts,reminder_sit_counts) values('"
				+ planeId
				+ "','"+plane.getPlaneName()
				+ "','"+plane.getPlaneImage()
				+ "','"+plane.getPlaneNo()
				+ "','"+plane.getUnitCost()
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

    @Override
    public OpenPage findPlaneList(OpenPage page, Plane plane) {
        StringBuffer sql = new StringBuffer();
        StringBuffer fromWhere = new StringBuffer();
        fromWhere.append("from t_airrent_plane ap inner join ");
        fromWhere.append("t_airrent_airline al on ap.airline_id=al.airline_id where 1=1 ");
        List<String> params = new ArrayList<String>();
        if (StringUtils.isNotBlank(plane.getPlaneName())) {
            fromWhere.append("and ap.plane_name like ? ");
            params.add("%"+plane.getPlaneName()+"%");
        }
        if (StringUtils.isNotBlank(plane.getAirlineName())) {
            fromWhere.append("and al.airline_name like ? ");
            params.add("%"+plane.getAirlineName()+"%");
        }
        if (StringUtils.isNotBlank(plane.getAirlineId())) {
            fromWhere.append("and al.airline_id = ? ");
            params.add(plane.getAirlineId());
        }
        if (StringUtils.isNotBlank(plane.getPlaneNo())) {
            fromWhere.append("and ap.plane_no like ? ");
            params.add("%"+plane.getPlaneNo()+"%");
        }
        sql.append("select count(*) ");
        long count = getJdbcTemplate().queryForObject(sql.append(fromWhere).toString(),params.toArray(),Long.class);
        if (count > 0) {
            page.setTotal(count);
            sql = new StringBuffer();
            sql.append("select * ");
            sql.append(fromWhere);
            sql.append(" limit " + page.getPageSize() + " OFFSET " + (page.getFirst() - 1) + "");
            List<Plane> list = getJdbcTemplate().query(sql.toString(),params.toArray(), new PlaneMapper());
            page.setRows(list);
        } else {
            page.setTotal(count);
            page.setRows(new ArrayList<Plane>());

        }
        return page;
    }

    @Override
    public String savePlane(Plane plane) {
        StringBuffer sql = new StringBuffer();
        Object[] params = new Object[17];
        if(StringUtils.isBlank(plane.getPlaneId())) {
            String planeId = CommonUtils.genUUID();
            sql.append("insert into t_airrent_plane(plane_id,plane_name,plane_image,plane_image_name," +
                    "plane_no,unit_cost,fly_unit_cost,plane_type,time_in_product,product_area,driving_mile," +
                    "speed,colour,show_unit_cost,plane_price,airline_id,sit_counts) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            params[0] = planeId;
            params[1] = plane.getPlaneName();
            params[2] = plane.getPlaneImage();
            params[3] = plane.getPlaneImageName();
            params[4] = plane.getPlaneNo();
            params[5] = plane.getUnitCost();
            params[6] = plane.getFlyUnitCost();
            params[7] = plane.getPlaneType();
            params[8] = plane.getTimeInProduct();
            params[9] = plane.getProductArea();
            params[10] = plane.getDrivingMile();
            params[11] = plane.getSpeed();
            params[12] = plane.getColour();
            params[13] = plane.getShowUnitCost();
            params[14] = plane.getPlanePrice();
            params[15] = plane.getAirlineId();
            params[16] = plane.getSitCounts();
        }else{
            sql.append("update t_airrent_plane set plane_name=?,plane_image=?,plane_image_name=?," +
                    "plane_no=?,unit_cost=?,fly_unit_cost=?,plane_type=?,time_in_product=?,product_area=?,driving_mile=?," +
                    "speed=?,colour=?,show_unit_cost=?,plane_price=?,airline_id=?,sit_counts=? " +
                    "where plane_id=?");
            params[0] = plane.getPlaneName();
            params[1] = plane.getPlaneImage();
            params[2] = plane.getPlaneImageName();
            params[3] = plane.getPlaneNo();
            params[4] = plane.getUnitCost();
            params[5] = plane.getFlyUnitCost();
            params[6] = plane.getPlaneType();
            params[7] = plane.getTimeInProduct();
            params[8] = plane.getProductArea();
            params[9] = plane.getDrivingMile();
            params[10] = plane.getSpeed();
            params[11] = plane.getColour();
            params[12] = plane.getShowUnitCost();
            params[13] = plane.getPlanePrice();
            params[14] = plane.getAirlineId();
            params[15] = plane.getSitCounts();
            params[16] = plane.getPlaneId();
        }
        int update = getJdbcTemplate().update(sql.toString(), params);
        if (update > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @Override
    public String updatePlaneStatus(Plane plane) {
        StringBuffer sql = new StringBuffer();
        String[] params = new String[2];
        sql.append("update t_airrent_plane set status=? where plane_id=? ");
        params[0] = plane.getStatus();
        params[1] = plane.getPlaneId();
        int update = getJdbcTemplate().update(sql.toString(), params);
        if (update > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }
}
