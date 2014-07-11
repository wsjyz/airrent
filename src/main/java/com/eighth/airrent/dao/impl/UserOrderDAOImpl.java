package com.eighth.airrent.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserOrderDAO;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.util.CommonUtils;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "UserOrderDAO")
public class UserOrderDAOImpl extends BaseDAO implements UserOrderDAO{

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public OpenPage<UserOrder> findUserOrder(OpenPage openPage, String userId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from t_airrent_user_order where user_id='"+userId+"'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if(count>0){
			openPage.setTotal(count);
			sql=new StringBuffer();
			sql.append("select * from t_airrent_user_order where user_id='"+userId+"' limit "+openPage.getPageSize()+" OFFSET "+(openPage.getFirst() - 1)+"");
			List<UserOrder> list=getJdbcTemplate().query(sql.toString(), new RowMapper<UserOrder>(){
				@Override
				public UserOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserOrder userOrder=new UserOrder();
				    userOrder.setOrderId(rs.getString("order_id"));//订单编号
				    userOrder.setUserId(rs.getString("user_id"));//提交人ID
				    userOrder.setAirportId(rs.getString("airport_id"));
				    //用途:商公务包机CHARTER|私人直升机PRIVATE_COPTER|航摄航拍AERIAL|农地森FARM|商业活动COMERCIAL|观光试飞TOUR|婚礼地产活动WEDDING_ESTATE|其它OTHER
				    userOrder.setOrderUse(rs.getString("order_use"));
				    userOrder.setStartTime(rs.getString("start_time"));//开始时间
				    userOrder.setEndTime(rs.getString("end_time"));//结束时间
				    userOrder.setStarting(rs.getString("starting"));//出发地
				    userOrder.setDestination(rs.getString("destination"));//目的地
				    userOrder.setUserCounts(rs.getInt("user_counts"));//人数
				    userOrder.setOptTime(rs.getString("opt_time"));//产生时间
				    userOrder.setDownPayment(rs.getBigDecimal("down_payment"));//定金
				    userOrder.setOrderCounts(rs.getInt("order_counts"));//预定数量
				    //支付状态 ONLINE_PAYED线上已支付 OFFLINE_PAYED线下已支付 NOT_PAY未支付
				    userOrder.setOrderStatus(rs.getString("order_status"));
				    userOrder.setDescription(rs.getString("description"));//备注
				return userOrder;
				}
			});
			openPage.setRows(list);
		}else{
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<UserOrder>());

		}
		return openPage;
	}

	@Override
	public String payOrder(String userId, String orderId) {
		return null;
	}

	@Override
	public UserOrder findOrderById(String orderId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_user_order where order_id='"+orderId+"'");
		List<UserOrder> list=getJdbcTemplate().query(sql.toString(), new RowMapper<UserOrder>(){
			@Override
			public UserOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserOrder userOrder=new UserOrder();
			    userOrder.setOrderId(rs.getString("order_id"));//订单编号
			    userOrder.setUserId(rs.getString("user_id"));//提交人ID
			    userOrder.setAirportId(rs.getString("airport_id"));
			    //用途:商公务包机CHARTER|私人直升机PRIVATE_COPTER|航摄航拍AERIAL|农地森FARM|商业活动COMERCIAL|观光试飞TOUR|婚礼地产活动WEDDING_ESTATE|其它OTHER
			    userOrder.setOrderUse(rs.getString("order_use"));
			    userOrder.setStartTime(rs.getString("start_time"));//开始时间
			    userOrder.setEndTime(rs.getString("end_time"));//结束时间
			    userOrder.setStarting(rs.getString("starting"));//出发地
			    userOrder.setDestination(rs.getString("destination"));//目的地
			    userOrder.setUserCounts(rs.getInt("user_counts"));//人数
			    userOrder.setOptTime(rs.getString("opt_time"));//产生时间
			    userOrder.setDownPayment(rs.getBigDecimal("down_payment"));//定金
			    userOrder.setOrderCounts(rs.getInt("order_counts"));//预定数量
			    //支付状态 ONLINE_PAYED线上已支付 OFFLINE_PAYED线下已支付 NOT_PAY未支付
			    userOrder.setOrderStatus(rs.getString("order_status"));
			    userOrder.setDescription(rs.getString("description"));//备注
			return userOrder;
			}
		});
		return list.get(0);
	}

	@Override
	public String deleteOrderById(String orderId) {
		StringBuffer sql=new StringBuffer();
		sql.append("delete from t_airrent_user_order where order_id='"+orderId+"'");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override
	public UserOrder addUserOrder(UserOrder order) {
		StringBuffer sql=new StringBuffer();
		String orderId=CommonUtils.genUUID();
		sql.append("INSERT into t_airrent_user_order(order_id,user_id,airport_id,order_use,start_time,end_time,"
				+ "starting,destination,user_counts,opt_time,down_payment,order_counts,order_status,description) values('"
				+orderId+ "','"+ order.getUserId()
				+ "','" + order.getAirportId()+ "','" + order.getOrderUse()+ "','" 
				+ order.getStartTime()+ "','" + order.getEndTime()
				+ "','" + order.getStarting()+ "','" + order.getDestination()
				+ "','" + order.getUserCounts()+ "','" + order.getOptTime()
				+ "','" + order.getDownPayment()+ "','" + order.getOrderCounts()
				+ "','" + order.getOrderStatus()+ "','" + order.getDescription()+ "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			StringBuffer sql1=new StringBuffer();
			sql1.append("select * from t_airrent_user_order where order_id='"+orderId+"'");
			List<UserOrder> list=getJdbcTemplate().query(sql1.toString(), new RowMapper<UserOrder>(){
				@Override
				public UserOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserOrder userOrder=new UserOrder();
					userOrder.setOrderId(rs.getString("order_id"));
					userOrder.setUserId(rs.getString("user_id"));//登录名
					userOrder.setAirportId(rs.getString("airport_id"));
					userOrder.setOrderUse(rs.getString("order_use"));//手机号
					userOrder.setStartTime(rs.getString("start_time"));//姓名
					userOrder.setEndTime(rs.getString("end_time"));//身份证号
					userOrder.setStarting(rs.getString("starting"));//MALE|FAMALE
					userOrder.setDestination(rs.getString("destination"));//登录提示信息
					userOrder.setUserCounts(rs.getInt("user_counts"));//年龄
				    userOrder.setOptTime(rs.getString("opt_time"));//居住地址
				    userOrder.setDownPayment(rs.getBigDecimal("down_payment"));//工作单位
				    userOrder.setOrderCounts(rs.getInt("order_counts"));//支付宝账号
				    userOrder.setOrderStatus(rs.getString("order_status"));//注册时的验证码
				    userOrder.setDescription(rs.getString("description"));//LOGIN_INFO_NULL请输入用户名密码
				return userOrder;
				}
			});
			return list.get(0);
		}
		return new UserOrder();
	}

   
}
