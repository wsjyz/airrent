package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eighth.airrent.domain.Airport;

import org.apache.commons.codec.digest.DigestUtils;
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
			line.setAirlineImageName(rs.getString("airline_image_name"));
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
				+ airlineId + "' and status='on'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if (count > 0) {
			openPage.setTotal(count);
			sql = new StringBuffer();
			sql.append("select * from t_airrent_plane where airline_id='"
					+ airlineId + "' and status='on' limit " + openPage.getPageSize()
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
			return plane;
		}

	}

	@Override
	public List<Airline> findAirlineAllById(String airportId,String address) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_airrent_airline where 1=1 ");
		if(StringUtils.isNotEmpty(airportId)){
			sql.append(" and  airport_id='"+ airportId + "'");
		}
		if(StringUtils.isNotEmpty(address)){
			sql.append("and address like '%"+ address + "%'");
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
        airline.setPassword(DigestUtils.md5Hex(airline.getPassword()));
//        getPoint(airline);
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
		if(StringUtils.isNotEmpty(airline.getPassword())){
			airline.setPassword(DigestUtils.md5Hex(airline.getPassword()));
		}
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

    @Override
    public OpenPage findAirlineList(OpenPage page, String airlineName, String loginName) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        List<String> params = new ArrayList<String>();
        sql.append("select count(*) from t_airrent_airline where 1=1 ");
        if (StringUtils.isNotBlank(airlineName)) {
            where.append("and airline_name like ? ");
            params.add("%"+airlineName+"%");
        }
        if (StringUtils.isNotBlank(loginName)) {
            where.append("and login_name like ? ");
            params.add("%"+loginName+"%");
        }

        long count = getJdbcTemplate().queryForObject(sql.append(where).toString(),params.toArray(),Long.class);
        if (count > 0) {
            page.setTotal(count);
            sql = new StringBuffer();
            sql.append("select * from t_airrent_airline where 1=1 ");
            sql.append(where);
            sql.append(" limit "
                    + page.getPageSize()
                    + " OFFSET " + (page.getFirst() - 1) + "");
            List<Airline> list = getJdbcTemplate().query(sql.toString(),params.toArray(),
                    new AirlineMapper());
            page.setRows(list);
        } else {
            page.setTotal(count);
            page.setRows(new ArrayList<Airline>());

        }
        return page;
    }

    @Override
    public String saveAirline(Airline airline) {
        StringBuffer sql = new StringBuffer();
        String[] params = new String[14];
        airline.setPassword(DigestUtils.md5Hex(airline.getPassword()));
//        getPoint(airline);
        if(StringUtils.isBlank(airline.getAirlineId())){
            String corpId = CommonUtils.genUUID();
            sql.append("INSERT into t_airrent_airline(airline_id,airline_name,airline_image,login_name,password,status,address,weixin,phone,airport_id,lat,lng,airline_image_name,intro) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            params[0]=corpId;
            params[1]=airline.getAirlineName();
            params[2]=airline.getAirlineImage();
            params[3]=airline.getLoginName();
            params[4]=airline.getPassword();
            params[5]=airline.getStatus();
            params[6]=airline.getAddress();
            params[7]=airline.getWeixin();
            params[8]=airline.getPhone();
            params[9]=airline.getAirportId();
            params[10]=airline.getLat();
            params[11]=airline.getLng();
            params[12]=airline.getAirlineImageName();
            params[13]=airline.getIntro();
        }else{
            sql.append("update t_airrent_airline set airline_name=?,airline_image=?,login_name=?,password=?,status=?,address=?,weixin=?,phone=?,airport_id=?,lat=?,lng=?,airline_image_name=?,intro=? " +
                    "where airline_id=?");
            params[0]=airline.getAirlineName();
            params[1]=airline.getAirlineImage();
            params[2]=airline.getLoginName();
            params[3]=airline.getPassword();
            params[4]=airline.getStatus();
            params[5]=airline.getAddress();
            params[6]=airline.getWeixin();
            params[7]=airline.getPhone();
            params[8]=airline.getAirportId();
            params[9]=airline.getLat();
            params[10]=airline.getLng();
            params[11]=airline.getAirlineImageName();
            params[12]=airline.getIntro();
            params[13]=airline.getAirlineId();
        }
        int update = getJdbcTemplate().update(sql.toString(),params);
        if (update > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

	@Override
	public List<Plane> findAllPlaneByAirlineId(String airlineId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_airrent_plane where airline_id='"
				+ airlineId + "'");
		
		List<Plane> list = getJdbcTemplate().query(sql.toString(),
				new PlaneMapper());
		return list;
	}

    @Override
    public Airline finAirline(String loginName, String password) {
        StringBuffer sql = new StringBuffer();
        password = DigestUtils.md5Hex(password);
        sql.append("select * from t_airrent_airline where 1=1 ");
        if(StringUtils.isNotEmpty(loginName)){
            sql.append(" and  login_name='"+ loginName + "'");
        }
        if(StringUtils.isNotEmpty(password)){
            sql.append("and password = '"+ password + "'");
        }
        List<Airline> list = getJdbcTemplate().query(sql.toString(),
                new AirlineMapper());
        if(!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String resetPassword(String airlineId, String newPassword) {
        StringBuffer sql=new StringBuffer();
        newPassword = DigestUtils.md5Hex(newPassword);
        sql.append("update t_airrent_airline set password='"+newPassword+"' where airline_id='"+airlineId+"'");
        int count=getJdbcTemplate().update(sql.toString());
        String status="FAIL";
        if (count>0) {
            status="SUCCESS";
        }
        return status;
    }
}
