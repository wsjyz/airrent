package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.CorpDAO;
import com.eighth.airrent.domain.Corp;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.util.CommonUtils;

@Repository(value = "CorpDAO")
public class CorpDAOImpl extends BaseDAO implements CorpDAO {

	@Override
	public String addCorp(Corp corp) {
		StringBuffer sql=new StringBuffer();
		String corpId=CommonUtils.genUUID();
		getPoint(corp);
		sql.append("INSERT into t_airrent_corp(corp_id,corp_name,login_name,password,status,address,weixin,phone,airport_id) values('"
				+corpId+ "','"+ corp.getCorpName()
				+ "','" + corp.getLoginName()+ "','" + corp.getPassword()+ "','" 
				+ corp.getStatus()+ "','" + corp.getAddress()
				+ "','" + corp.getWeixin()+ "','" + corp.getPhone()
				+ "','" + corp.getAirport()+"')");
		int update = getJdbcTemplate().update(sql.toString());
		String status="FAIL";
		if (update>0) {
			status="SUCCESS";
		}
		return status;
	}
	 public void getPoint(Corp corp ){
	     try {  
	            String sCurrentLine;  
	            String sTotalString;  
	            sCurrentLine = "";  
	            sTotalString = "";  
	            java.io.InputStream l_urlStream;  
	            java.net.URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address="+corp.getAddress().replaceAll(" ", "")+"&output=json&ak=702632E1add3d4953d0f105f27c294b9&callback=showLocation");  
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
	           corp.setLng(a[1]);
	           s1="}"+","+"\"";
	          String a1[]=strs[1].split(s1, 2);
	          corp.setLat(a1[0]);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    
	  }

	@Override
	public OpenPage<Corp> getCorpPage(OpenPage openPage) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_airrent_corp ");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if (count > 0) {
			openPage.setTotal(count);
			sql = new StringBuffer();
			sql.append("select * from t_airrent_corp  limit "
					+ openPage.getPageSize()
					+ " OFFSET " + (openPage.getFirst() - 1));
			List<Corp> list = getJdbcTemplate().query(sql.toString(),
					new CorpMapper());
			openPage.setRows(list);
		} else {
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<Corp>());

		}
		return openPage;
	}

	@Override
	public Corp getCorpById(String corpId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_airrent_corp  where corp_id='"+corpId+"'");
		List<Corp> list = getJdbcTemplate().query(sql.toString(),
				new CorpMapper());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

    public class CorpMapper implements RowMapper<Corp>{
    	@Override
		public Corp mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Corp corp = new Corp();
			corp.setCorpId(rs.getString("corp_id"));
			corp.setCorpName(rs.getString("corp_name"));
			corp.setLoginName(rs.getString("login_name"));
			corp.setPassword(rs.getString("password"));
			corp.setStatus(rs.getString("status"));
			corp.setAddress(rs.getString("address"));
			corp.setWeixin(rs.getString("weixin"));
			corp.setPhone(rs.getString("phone"));
			corp.setAirportId(rs.getString("airport_id"));
			return corp;
		}
    }
	@Override
	public String updateCorp(Corp corp) {
		StringBuffer sql=new StringBuffer();
		sql.append("update t_airrent_corp set corp_name='"+corp.getCorpName()+"',login_name='"+corp.getLoginName()+"',password='"+corp.getPassword()
				+"',status='"+corp.getStatus()+"',address='"+corp.getAddress()+"',weixin='"+corp.getWeixin()+"',phone='"+corp.getPhone()
				+"',airport_id='"+corp.getAirportId()+"' where corp_id='"+corp.getCorpId()+"'");
		int count=getJdbcTemplate().update(sql.toString());
		String status="FAIL";
		if (count>0) {
			status="SUCCESS";
		}
		return status;
	}

	@Override
	public String deleteCorp(String corpId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_airrent_corp where corp_id='"
				+ corpId + "'");
		int update = getJdbcTemplate().update(sql.toString());
		if (update > 0) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	
}
