package com.eighth.airrent.dao.impl;

import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.domain.Airline;
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
		sql.append("select count(*) from t_airrent_airport");
		if (!StringUtils.isEmpty(airportName)) {
			sql.append(" where airport_name like '%"
				+ airportName + "%'");
		}
		 
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if (count > 0) {
			openPage.setTotal(count);
			sql = new StringBuffer();
			sql.append("select * from t_airrent_airport");
			if (!StringUtils.isEmpty(airportName)) {
				sql.append(" where airport_name like '%"
					+ airportName + "%'");
			}
			sql.append(" limit "
					+ openPage.getPageSize()
					+ " OFFSET " + (openPage.getFirst() - 1) + "");
			List<Airport> list = getJdbcTemplate().query(sql.toString(),
					new AirportMapper());
			openPage.setRows(list);
		} else {
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Airport>());

		}
		return openPage;
	}
    public class AirportMapper implements RowMapper<Airport>{
    	@Override
		public Airport mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Airport airport = new Airport();
			airport.setAirportId(rs.getString("airport_id"));
			airport.setAirportImage(rs.getString("airport_image"));
			airport.setAirportName(rs.getString("airport_name"));
			airport.setDescription(rs.getString("description"));
			airport.setLat(rs.getString("lat"));
			airport.setLng(rs.getString("lng"));
			airport.setAddress(rs.getString("address"));
			return airport;
		}
    }
    public void getPoint(Airport airport ){
	     try {  
	            java.io.InputStream l_urlStream;  
	            java.net.URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address="+airport.getDescription().replaceAll(" ", "")+"&output=json&ak=106f91b50bc8da14c6ce7d456f2e3e50&callback=showLocation");  
	            java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();  
	            l_connection.connect();  
	            l_urlStream = l_connection.getInputStream();  
	            java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));   
	            String str=l_reader.readLine();
	            //用经度分割返回的网页代码
	            String s=","+"\""+"lat"+"\""+":";
	            String strs[]=str.split(s, 2);
	            String s1="\""+"lng"+"\""+":";
	           String a[]=strs[0].split(s1, 2);
	           airport.setLng(a[1]);
	           s1="}"+","+"\"";
	          String a1[]=strs[1].split(s1, 2);
	          airport.setLat(a1[0]);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    
	  }

	@Override
	public Airport findAirportById(String airportId) {
		StringBuffer sql = new StringBuffer(
				"select * from t_airrent_airport where airport_id='"
						+ airportId + "' ");

		List<Airport> list = getJdbcTemplate().query(sql.toString(),
				new AirportMapper());
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
					new PlaneMapper());
			openPage.setRows(list);
		} else {
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
	public String addAirport(Airport airport) {
		StringBuffer sql = new StringBuffer();
		String airportId = CommonUtils.genUUID();
//		getPoint(airport);
		sql.append("INSERT into t_airrent_airport(airport_id,airport_name,description,airport_image,lat,lng) values('"
				+ airportId
				+ "','"
				+ airport.getAirportName()
				+ "','"
				+ airport.getDescription()
				+ "','"
				+ airport.getAirportImage()
				+ "','"
				+ airport.getLat()
				+ "','"
				+ airport.getLng()
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

	@Override
	public List<Airport> findAllAirport(String address) {
		StringBuffer sql = new StringBuffer("select * from t_airrent_airport");
		if (StringUtils.isNotEmpty(address)) {
			sql.append(" where description like '%"+address+"%'");
		}
		List<Airport> list = getJdbcTemplate().query(sql.toString(),
				new AirportMapper());
		return list;
	}

    @Override
    public OpenPage<Airport> findAirportList(OpenPage<Airport> page, String airportName, String address) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        List<String> params = new ArrayList<String>();
        sql.append("select count(*) from t_airrent_airport where 1=1 ");
        if (StringUtils.isNotBlank(airportName)) {
            where.append("and airport_name like ? ");
            params.add("%"+airportName+"%");
        }
        if (StringUtils.isNotBlank(address)) {
            where.append("and address like ? ");
            params.add("%"+address+"%");
        }

        long count = getJdbcTemplate().queryForObject(sql.append(where).toString(),params.toArray(),Long.class);
        if (count > 0) {
            page.setTotal(count);
            sql = new StringBuffer();
            sql.append("select * from t_airrent_airport where 1=1 ");
            sql.append(where);
            sql.append(" limit "
                    + page.getPageSize()
                    + " OFFSET " + (page.getFirst() - 1) + "");
            List<Airport> list = getJdbcTemplate().query(sql.toString(),params.toArray(),
                    new AirportMapper());
            page.setRows(list);
        } else {
            page.setTotal(count);
            page.setRows(new ArrayList<Airport>());

        }
        return page;
    }

    @Override
    public String saveAirport(Airport airport) {
        StringBuffer sql = new StringBuffer();
        String[] params = new String[6];
        String airportId = CommonUtils.genUUID();
//        getPoint(airport);
        sql.append("INSERT into t_airrent_airport(airport_id,airport_name,description,address,lat,lng) values(?,?,?,?,?,?)");
        params[0]=airportId;
        params[1]=airport.getAirportName();
        params[2]=airport.getDescription();
        params[3]=airport.getAddress();
        params[4]=airport.getLat();
        params[5]=airport.getLng();
        int update = getJdbcTemplate().update(sql.toString(),params);
        if (update > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    public List<Airport> findAllAirport() {
        StringBuffer sql = new StringBuffer("select * from t_airrent_airport");
        List<Airport> list = getJdbcTemplate().query(sql.toString(),new AirportMapper());
        return list;
    }
}
