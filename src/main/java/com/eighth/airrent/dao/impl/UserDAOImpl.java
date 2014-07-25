package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserDAO;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.util.CommonUtils;

/**
 * Created by dam on 2014/6/27.
 */
@Repository(value = "UserDAO")
public class UserDAOImpl  extends BaseDAO implements UserDAO {

	@Override
	public UserInfo login(String loginName, String password) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_user_info where login_name='"+loginName+"' and password='"+password+"'");
		List<UserInfo> list=getJdbcTemplate().query(sql.toString(), new RowMapper<UserInfo>(){
			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserInfo userInfo=new UserInfo();
					userInfo.setUserId(rs.getString("user_id"));
				    userInfo.setLoginName(rs.getString("login_name"));//登录名
				    userInfo.setPassword(rs.getString("password"));
				    userInfo.setMobile(rs.getString("mobile"));//手机号
				    userInfo.setUserName(rs.getString("user_name"));//姓名
				    userInfo.setIdentityCard(rs.getString("identity_card"));//身份证号
				    userInfo.setSex(rs.getString("sex"));//MALE|FAMALE
				    userInfo.setAge(rs.getString("age"));//年龄
				    userInfo.setAddress(rs.getString("address"));//居住地址
				    userInfo.setWorkOrg(rs.getString("work_org"));//工作单位
				    userInfo.setZhifubao(rs.getString("zhifubao"));//支付宝账号
				    userInfo.setRegistToken(rs.getString("registToken"));//注册时的验证码
				    userInfo.setLoginTip(rs.getString("login_tip"));//登录提示信息
				    userInfo.setLoginStatus(rs.getString("login_status"));//LOGIN_INFO_NULL请输入用户名密码
			return userInfo;
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return new UserInfo();
		}
		return list.get(0);

	}

	@Override
	public UserInfo regist(UserInfo userInfo) {
		StringBuffer sql=new StringBuffer();
		String userId=CommonUtils.genUUID();
		sql.append("INSERT into t_airrent_user_info(user_id,login_name,password,mobile,user_name,identity_card,sex,age,address,work_org,zhifubao,registToken,login_tip,login_status) values('"
				+userId+ "','"+ userInfo.getLoginName()
				+ "','" + userInfo.getPassword()+ "','" + userInfo.getMobile()+ "','" 
				+ userInfo.getUserName()+ "','" + userInfo.getIdentityCard()
				+ "','" + userInfo.getSex()+ "','" + userInfo.getAge()
				+ "','" + userInfo.getAddress()+ "','" + userInfo.getWorkOrg()
				+ "','" + userInfo.getZhifubao()+ "','" + userInfo.getRegistToken()
				+ "','" + userInfo.getLoginTip()+ "','" + userInfo.getLoginStatus()+ "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			StringBuffer sql1=new StringBuffer();
			sql1.append("select * from t_airrent_user_info where user_id='"+userId+"'");
			List<UserInfo> list=getJdbcTemplate().query(sql1.toString(), new RowMapper<UserInfo>(){
				@Override
				public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserInfo userInfo=new UserInfo();
						userInfo.setUserId(rs.getString("user_id"));
					    userInfo.setLoginName(rs.getString("login_name"));//登录名
					    userInfo.setPassword(rs.getString("password"));
					    userInfo.setMobile(rs.getString("mobile"));//手机号
					    userInfo.setUserName(rs.getString("user_name"));//姓名
					    userInfo.setIdentityCard(rs.getString("identity_card"));//身份证号
					    userInfo.setSex(rs.getString("sex"));//MALE|FAMALE
					    userInfo.setAge(rs.getString("age"));//年龄
					    userInfo.setAddress(rs.getString("address"));//居住地址
					    userInfo.setWorkOrg(rs.getString("work_org"));//工作单位
					    userInfo.setZhifubao(rs.getString("zhifubao"));//支付宝账号
					    userInfo.setRegistToken(rs.getString("registToken"));//注册时的验证码
					    userInfo.setLoginTip(rs.getString("login_tip"));//登录提示信息
					    userInfo.setLoginStatus(rs.getString("login_status"));//LOGIN_INFO_NULL请输入用户名密码
				return userInfo;
				}
			});
			return list.get(0);
		}
		return new UserInfo();
	}

	@Override
	public String obtainVerifyCode() {
		StringBuffer sql=new StringBuffer();
		String tokenId=CommonUtils.genUUID();
		Calendar cal=Calendar.getInstance();
		 int random=(int)(Math.random()*10000);
		String token=random+"";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sql.append("INSERT into t_airrent_verify_code(token_id,token,opt_time) values('"
				+tokenId+ "','"+token+ "','" +sdf.format(cal.getTime())+ "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			return token;
		}
		return null;
	}

	@Override
	public String checkVerifyCode(String code) {
		StringBuffer sql1=new StringBuffer();
		sql1.append("select * from t_airrent_verify_code where token='"+code+"' order by opt_time desc ");
		List<VerifyCode> list=getJdbcTemplate().query(sql1.toString(), new RowMapper<VerifyCode>(){
			@Override
			public VerifyCode mapRow(ResultSet rs, int rowNum) throws SQLException {
				VerifyCode VerifyCode=new VerifyCode();
				VerifyCode.setTokenId(rs.getString("token_id"));
				VerifyCode.setToken(rs.getString("token"));
				VerifyCode.setOptTime(rs.getString("opt_time"));
			return VerifyCode;
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return "FAIL";
		}else{
			
			String result="PAST";
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
			for (VerifyCode verifyCode : list) {
				try {
					long time = cal.getTimeInMillis()-(sdf.parse(verifyCode.getOptTime())).getTime();
					if (time<5*60*1000) {//当前提交时间距离验证码创建时间少于5分钟
						result="RIGHT";
						break;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return result;
		}
	}

	@Override
	public String resetPassword(String mobile, String newPassword) {
		StringBuffer sql=new StringBuffer();
		sql.append("update t_airrent_user_info set password='"+newPassword+"' where mobile='"+mobile+"'");
		int count=getJdbcTemplate().update(sql.toString());
		String status="FAIL";
		if (count>0) {
			status="SUCCESS";
		}
		return status;
	}

	@Override
	public String modifyUserInfo(UserInfo userInfo) {
		StringBuffer sql=new StringBuffer();
		sql.append("update t_airrent_user_info set login_name='"+userInfo.getLoginName()+"',password='"+userInfo.getPassword()+"',mobile='"+userInfo.getMobile()
				+"',user_name='"+userInfo.getUserName()+"',identity_card='"+userInfo.getIdentityCard()+"',sex='"+userInfo.getSex()+"',age='"+userInfo.getAge()
				+"',address='"+userInfo.getAddress()+"',work_org='"+userInfo.getWorkOrg()+"',zhifubao='"+userInfo.getZhifubao()+"',registToken='"+userInfo.getRegistToken()
				+"',login_tip='"+userInfo.getLoginTip()+"',login_status='"+userInfo.getLoginStatus()+"' where user_id='"+userInfo.getUserId()+"'");
		int count=getJdbcTemplate().update(sql.toString());
		String status="FAIL";
		if (count>0) {
			status="SUCCESS";
		}
		return status;
	}

	@Override
	public UserInfo getById(String userId) {
		StringBuffer sql1=new StringBuffer();
		sql1.append("select * from t_airrent_user_info where user_id='"+userId+"'");
		List<UserInfo> list=getJdbcTemplate().query(sql1.toString(), new RowMapper<UserInfo>(){
			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserInfo userInfo=new UserInfo();
					userInfo.setUserId(rs.getString("user_id"));
				    userInfo.setLoginName(rs.getString("login_name"));//登录名
				    userInfo.setPassword(rs.getString("password"));
				    userInfo.setMobile(rs.getString("mobile"));//手机号
				    userInfo.setUserName(rs.getString("user_name"));//姓名
				    userInfo.setIdentityCard(rs.getString("identity_card"));//身份证号
				    userInfo.setSex(rs.getString("sex"));//MALE|FAMALE
				    userInfo.setAge(rs.getString("age"));//年龄
				    userInfo.setAddress(rs.getString("address"));//居住地址
				    userInfo.setWorkOrg(rs.getString("work_org"));//工作单位
				    userInfo.setZhifubao(rs.getString("zhifubao"));//支付宝账号
				    userInfo.setRegistToken(rs.getString("registToken"));//注册时的验证码
				    userInfo.setLoginTip(rs.getString("login_tip"));//登录提示信息
				    userInfo.setLoginStatus(rs.getString("login_status"));//LOGIN_INFO_NULL请输入用户名密码
			return userInfo;
			}
		});
		return list.get(0);
	}

  
}
