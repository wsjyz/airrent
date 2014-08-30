package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.eighth.airrent.util.CommonUtils;

@Repository(value = "AirlineDAO")
public class AirlineDAOImpl extends BaseDAO implements AirlineDAO {

	@Override
	public Airline findAirlineById(String airlineId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_airrent_airline where airline_id='"
				+ airlineId + "'");
		List<Airline> list = getJdbcTemplate().query(sql.toString(),
				new AirlineMapper());
		Airline airline=new Airline();
		if (CollectionUtils.isEmpty(list)) {
			return airline;
		}else{
			 airline=list.get(0);
			 if(StringUtils.isNotEmpty(airline.getAirlineId())){
				 sql = new StringBuffer();
				sql.append("select * from t_airrent_plane where airline_id='"
							+ airlineId + "'");
				List<Plane> planelist = getJdbcTemplate().query(sql.toString(),
						new PlaneMapper());
				airline.setPlaneList(planelist);
			 }
		}
		return airline;
	}

	public class AirlineMapper implements RowMapper<Airline> {
		@Override
		public Airline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Airline line = new Airline();
			line.setAirlineId(rs.getString("airline_id"));
			line.setAirlineImage(rs.getString("airline_image"));
			line.setAirlineName(rs.getString("airline_name"));
			line.setAirportId(rs.getString("airport_id"));
			line.setLoginName(rs.getString("login_name"));
			line.setPassword(rs.getString("password"));
			line.setStatus(rs.getString("status"));
			line.setAddress(rs.getString("address"));
			line.setWeixin(rs.getString("weixin"));
			line.setPhone(rs.getString("phone"));
			line.setLat(rs.getString("lat"));
			line.setLng(rs.getString("lng"));
			return line;
		}

	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,
			String airlineId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_airrent_plane where airline_id='"
				+ airlineId + "'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if (count > 0) {
			openPage.setTotal(count);
			sql = new StringBuffer();
			sql.append("select * from t_airrent_plane where airline_id='"
					+ airlineId + "' limit " + openPage.getPageSize()
					+ " OFFSET " + (openPage.getFirst() - 1) + "");
			List<Plane> list = getJdbcTemplate().query(sql.toString(),
					new PlaneMapper());
			openPage.setRows(list);
		} else {
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Plane>());

		}
		return openPage;

	}

	public class PlaneMapper implements RowMapper<Plane> {
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
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_airrent_airline ");
		if(StringUtils.isNotEmpty(airportId)){
			sql.append("where airport_id='"+ airportId + "'");
		}
		List<Airline> list = getJdbcTemplate().query(sql.toString(),
				new AirlineMapper());

		return list;
	}

	public void getPoint(Airline airline) {
		try {
			String sCurrentLine;
			String sTotalString;
			sCurrentLine = "";
			sTotalString = "";
			java.io.InputStream l_urlStream;
			java.net.URL l_url = new java.net.URL(
					"http://api.map.baidu.com/geocoder/v2/?address="
							+ airline.getAddress().replaceAll(" ", "")
							+ "&output=json&ak=106f91b50bc8da14c6ce7d456f2e3e50&callback=showLocation");
			java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url
					.openConnection();
			l_connection.connect();
			l_urlStream = l_connection.getInputStream();
			java.io.BufferedReader l_reader = new java.io.BufferedReader(
					new java.io.InputStreamReader(l_urlStream));
			String str = l_reader.readLine();
			// 用经度分割返回的网页代码
			String s = "," + "\"" + "lat" + "\"" + ":";
			String strs[] = str.split(s, 2);
			String s1 = "\"" + "lng" + "\"" + ":";
			String a[] = strs[0].split(s1, 2);
			airline.setLng(a[1]);
			s1 = "}" + "," + "\"";
			String a1[] = strs[1].split(s1, 2);
			airline.setLat(a1[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String addAirline(Airline airline) {
		StringBuffer sql = new StringBuffer();
		String corpId = CommonUtils.genUUID();
		getPoint(airline);
		sql.append("INSERT into t_airrent_airline(airline_id,airline_name,airline_image,login_name,password,status,address,weixin,phone,airport_id,lat,lng) values('"
				+ corpId
				+ "','"
				+ airline.getAirlineName()
				+ "','"
				+ airline.getAirlineImage()
				+ "','"
				+ airline.getLoginName()
				+ "','"
				+ airline.getPassword()
				+ "','"
				+ airline.getStatus()
				+ "','"
				+ airline.getAddress()
				+ "','"
				+ airline.getWeixin()
				+ "','"
				+ airline.getPhone()
				+ "','"
				+ airline.getAirportId()
				+ "','"
				+ airline.getLat()
				+ "','"
				+ airline.getLng() + "')");
		int update = getJdbcTemplate().update(sql.toString());
		String status = "FAIL";
		if (update > 0) {
			status = "SUCCESS";
		}
		return status;
	}

	@Override
	public String updateAirline(Airline airline) {
		StringBuffer sql=new StringBuffer();
		sql.append("update t_airrent_airline set ");
		if(StringUtils.isNotEmpty(airline.getAirlineName())){
			sql.append(" airline_name='"+airline.getAirlineName()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getLoginName())){
			sql.append(" login_name='"+airline.getLoginName()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getPassword())){
			sql.append("password='"+airline.getPassword()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getStatus())){
			sql.append(" status='"+airline.getStatus()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getAddress())){
			sql.append(" address='"+airline.getAddress()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getWeixin())){
			sql.append(" weixin='"+airline.getWeixin()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getPhone())){
			sql.append(" phone='"+airline.getPhone()+"',");
		}
		if(StringUtils.isNotEmpty(airline.getAirportId())){
			sql.append(" airport_id='"+airline.getAirportId()+"',");
		} 
		if(sql.lastIndexOf(",") + 1 == sql.length()){
            sql.delete(sql.lastIndexOf(","),sql.length());
        }
		sql.append(" where airline_id='"+airline.getAirlineId()+"'");
		int count=getJdbcTemplate().update(sql.toString());
		String status="FAIL";
		if (count>0) {
			status="SUCCESS";
		}
		return status;
	}

	@Override
	public String deleteAirline(String airlineId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_airrent_airline where airline_id='"
				+ airlineId + "'");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}
}
