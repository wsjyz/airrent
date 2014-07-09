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
		return null;
	}

   
}
