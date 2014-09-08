package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.domain.VerifyCode;
import com.eighth.airrent.util.AirrentUtils;
import com.eighth.airrent.util.CommonUtils;

/**
 * Created by dam on 2014/6/27.
 */
@Repository(value = "UserDAO")
public class UserDAOImpl  extends BaseDAO implements UserDAO {

	@Override
	public UserInfo login(String loginName, String password) {
		UserInfo userInfo=new UserInfo();
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
			userInfo.setHint(AirrentUtils.LOGIN_INFO_NULL);
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_user_info where login_name='"+loginName+"' and password='"+password+"'");
		List<UserInfo> list=getJdbcTemplate().query(sql.toString(), new UserInfoMapper());
		if (CollectionUtils.isEmpty(list)) {
			userInfo.setHint(AirrentUtils.NAME_PASSWORD_ERROR);
		}else{
			userInfo= list.get(0);
			userInfo.setHint(AirrentUtils.LOGIN_SUCCESS);
		}
		return userInfo;

	}
	 public class UserInfoMapper implements RowMapper<UserInfo>{
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
				    userInfo.setType(rs.getString("type"));//用户类型 管理员；普通账号
			return userInfo;
			}
	    }
	@Override
	public UserInfo regist(UserInfo userInfo) {
		StringBuffer sql=new StringBuffer();
		//检查是否已注册
		StringBuffer sql1=new StringBuffer();
		sql1.append("select * from t_airrent_user_info where login_name='"+userInfo.getLoginName()+"'");
		List<UserInfo> list=getJdbcTemplate().query(sql1.toString(), new UserInfoMapper());
		if (!CollectionUtils.isEmpty(list)) {
			userInfo.setHint(AirrentUtils.REGIST_EXISTS);
		}else{
			String userId=CommonUtils.genUUID();
			sql.append("INSERT into t_airrent_user_info(user_id,login_name,password,mobile,user_name,identity_card,sex,age,address,work_org,zhifubao,registToken,login_tip) values('"
					+userId+ "','"+ userInfo.getLoginName()
					+ "','" + userInfo.getPassword()+ "','" + userInfo.getMobile()+ "','" 
					+ userInfo.getUserName()+ "','" + userInfo.getIdentityCard()
					+ "','" + userInfo.getSex()+ "','" + userInfo.getAge()
					+ "','" + userInfo.getAddress()+ "','" + userInfo.getWorkOrg()
					+ "','" + userInfo.getZhifubao()+ "','" + userInfo.getRegistToken()
					+ "','" + userInfo.getLoginTip()+ "')");
			int update = getJdbcTemplate().update(sql.toString());
			if (update>0) {
				userInfo=getById(userId);
				userInfo.setHint(AirrentUtils.REGIST_SUCCESS);
			}else{

				userInfo.setUserId(userId);
				userInfo.setHint(AirrentUtils.REGIST_FAIL);
			}
		}
		return userInfo;
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
	public UserInfo modifyUserInfo(UserInfo userInfo) {
		StringBuffer sql=new StringBuffer();
		sql.append("update t_airrent_user_info set ");
		  if(!StringUtils.isEmpty(userInfo.getLoginName())){
	            sql.append("login_name='"+userInfo.getLoginName()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getPassword())){
	            sql.append("password='"+userInfo.getPassword()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getMobile())){
	            sql.append("mobile='"+userInfo.getMobile()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getUserName())){
	            sql.append("user_name='"+userInfo.getUserName()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getIdentityCard())){
	            sql.append("dentity_card='"+userInfo.getIdentityCard()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getSex())){
	            sql.append("sex='"+userInfo.getSex()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getAge())){
	            sql.append("age='"+userInfo.getAge()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getAddress())){
	            sql.append("address='"+userInfo.getAddress()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getWorkOrg())){
	            sql.append("work_org='"+userInfo.getWorkOrg()+"',");
	        }

		  if(!StringUtils.isEmpty(userInfo.getZhifubao())){
	            sql.append("zhifubao='"+userInfo.getZhifubao()+"',");
	        }
		  if(!StringUtils.isEmpty(userInfo.getRegistToken())){
	            sql.append("registToken='"+userInfo.getRegistToken()+"',");
	        }
		  if(!StringUtils.isEmpty(userInfo.getLoginTip())){
	            sql.append("login_tip='"+userInfo.getLoginTip()+"',");
	        }
		  if(sql.lastIndexOf(",") + 1 == sql.length()){
	            sql.delete(sql.lastIndexOf(","),sql.length());
	        }
		 sql.append("where user_id='"+userInfo.getUserId()+"'");
		int count=getJdbcTemplate().update(sql.toString());
		if (count>0) {
			return userInfo;
		}
		return null;
	}

	@Override
	public UserInfo getById(String userId) {
		StringBuffer sql1=new StringBuffer();
		sql1.append("select * from t_airrent_user_info where user_id='"+userId+"'");
		List<UserInfo> list=getJdbcTemplate().query(sql1.toString(), new UserInfoMapper());
		if (CollectionUtils.isEmpty(list)) {
			return new UserInfo();
		}
		return list.get(0);

	}

    @Override
    public UserInfo find(UserInfo userInfo) {
        StringBuffer sql=new StringBuffer();
        List<String> params = new ArrayList<String>();
        sql.append("select * from t_airrent_user_info where 1=1 ")
                .append("and login_name=? ")
                .append("and password=? ")
                .append("and type=? limit 1");
        params.add(userInfo.getLoginName());
        params.add(userInfo.getPassword());
        params.add(userInfo.getType());
        List<UserInfo> list=getJdbcTemplate().query(sql.toString(),params.toArray(), new UserInfoMapper());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


}
