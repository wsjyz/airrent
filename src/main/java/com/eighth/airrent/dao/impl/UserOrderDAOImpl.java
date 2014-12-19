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

import com.alipay.util.UtilDate;
import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserDAO;
import com.eighth.airrent.dao.UserOrderDAO;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.util.CommonUtils;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "UserOrderDAO")
public class UserOrderDAOImpl extends BaseDAO implements UserOrderDAO{

	@Autowired
	AirportDAO airportDao;
    @Autowired
    UserDAO userInfoDao;
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public OpenPage<UserOrder> findUserOrder(OpenPage openPage, String userId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from t_airrent_user_order where user_id='"+userId+"'");
		int count = getJdbcTemplate().queryForInt(sql.toString());
		if(count>0){
			openPage.setTotal(count);
			sql=new StringBuffer();
			sql.append("select * from t_airrent_user_order where user_id='"+userId+"' order by opt_time desc limit "+openPage.getPageSize()+" OFFSET "+(openPage.getFirst() - 1)+"");
			List<UserOrder> list=getJdbcTemplate().query(sql.toString(), new UserOrderMapper());
			openPage.setRows(list);
		}else{
			openPage.setTotal(count);
			openPage.setRows(new ArrayList<UserOrder>());

		}
		return openPage;
	}
	 public class UserOrderMapper implements RowMapper<UserOrder>{
		 @Override
			public UserOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserOrder userOrder=new UserOrder();
			    userOrder.setOrderId(rs.getString("order_id"));//订单编号
			    userOrder.setUserId(rs.getString("user_id"));//提交人ID
			    userOrder.setAirportId(rs.getString("airport_id"));
			    //用途:商公务包机CHARTER|私人直升机PRIVATE_COPTER|航摄航拍AERIAL|农地森FARM|商业活动COMERCIAL|观光试飞TOUR|婚礼地产活动WEDDING_ESTATE|其它OTHER
			    userOrder.setOrderUse(rs.getString("order_use"));
			    userOrder.setStartDate(rs.getString("start_date"));//开始时间
			    userOrder.setEndDate(rs.getString("end_date"));//结束时间
			    userOrder.setStayHour(rs.getBigDecimal("stay_hour"));//停留时间
			    userOrder.setFlyHour(rs.getBigDecimal("fly_hour"));//飞行时间
			    userOrder.setStarting(rs.getString("start_ing"));//出发地
			    userOrder.setDestination(rs.getString("destination"));//目的地
			    userOrder.setUserCounts(rs.getInt("user_counts"));//人数
			    userOrder.setOptTime(rs.getString("opt_time"));//产生时间
			    userOrder.setDownPayment(rs.getBigDecimal("down_payment"));//定金
			    userOrder.setOrderCounts(rs.getInt("order_counts"));//预定数量
			    //支付状态 ONLINE_PAYED线上已支付 OFFLINE_PAYED线下已支付 NOT_PAY未支付
			    userOrder.setOrderStatus(rs.getString("order_status"));
			    userOrder.setDescription(rs.getString("description"));//备注
                userOrder.setPlaneId(rs.getString("plane_id"));
                userOrder.setOrderLetter(rs.getString("order_letter"));
                userOrder.setOrderNumber(rs.getString("order_number"));
             return userOrder;
			}
	    }
	@Override
	public String payOrder(String userId, String orderId) throws Exception {
		UserOrder userOrder = findOrderById(orderId);
		return null;
	}

	@Override
	public UserOrder findOrderById(String orderId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_airrent_user_order where order_id='"+orderId+"'");
		List<UserOrder> list=getJdbcTemplate().query(sql.toString(), new UserOrderMapper());
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
		sql.append("INSERT into t_airrent_user_order(order_id,user_id,airport_id,order_use,start_date,end_date,"
				+ "start_ing,destination,user_counts,opt_time,down_payment,order_counts,order_status,description,fly_hour,stay_hour,order_letter,order_number,plane_id) values('"
				+orderId+ "','"+ order.getUserId()
				+ "','" + order.getAirportId()+ "','" + order.getOrderUse()+ "','" 
				+ order.getStartDate()+ "','" + order.getEndDate()
				+ "','" + order.getStarting()+ "','" + order.getDestination()
				+ "','" + order.getUserCounts()+ "','" + order.getOptTime()
				+ "','" + order.getDownPayment()+ "','" + order.getOrderCounts()
				+ "','" + order.getOrderStatus()+ "','" + order.getDescription()+ "','" 
				+ order.getFlyHour()+ "','" + order.getStayHour()+ "','" 
				+ order.getOrderLetter()+ "','" + order.getOrderNumber()+ "','"
				+ order.getPlaneId()+ "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			StringBuffer sql1=new StringBuffer();
			sql1.append("select * from t_airrent_user_order where order_id='"+orderId+"'");
			List<UserOrder> list=getJdbcTemplate().query(sql1.toString(), new UserOrderMapper());
			return list.get(0);
		}
		return new UserOrder();
	}

    @Override
    public OpenPage findUserOrders(OpenPage openPage, UserOrder userOrder) {
        StringBuffer sql=new StringBuffer();
        StringBuffer wheresql=new StringBuffer();
        List<String> params = new ArrayList<String>();
        wheresql.append("from t_airrent_user_order uo inner join t_airrent_plane ap on uo.plane_id=ap.plane_id ");
        wheresql.append(" inner join t_airrent_user_info ui on uo.user_id=ui.user_id where 1=1 ");
        if (StringUtils.isNotBlank(userOrder.getOrderLetter())) {
            wheresql.append(" and uo.order_letter = ? ");
            params.add(userOrder.getOrderLetter());
        }
        if (StringUtils.isNotBlank(userOrder.getOrderNumber())) {
            wheresql.append(" and uo.order_number = ? ");
            params.add(userOrder.getOrderNumber());
        }
        if(StringUtils.isNotBlank(userOrder.getLoginName())){
            wheresql.append(" and ui.login_name = ? ");
            params.add(userOrder.getLoginName());
        }
        if(StringUtils.isNotBlank(userOrder.getPlaneName())){
            wheresql.append(" and ap.plane_name = ? ");
            params.add(userOrder.getPlaneName());
        }
        if(StringUtils.isNotBlank(userOrder.getAirportId())){
            wheresql.append(" and uo.airport_id = ? ");
            params.add(userOrder.getAirportId());
        }
        sql.append("select count(*) ").append(wheresql);
        int count = getJdbcTemplate().queryForInt(sql.toString(),params.toArray());
        if(count>0){
            openPage.setTotal(count);
            sql=new StringBuffer();
            sql.append("select * ").append(wheresql).append(" limit "+openPage.getPageSize()+" OFFSET "+(openPage.getFirst() - 1));
            List<UserOrder> list=getJdbcTemplate().query(sql.toString() ,params.toArray(), new UserOrderMapper());
            for (UserOrder uo : list) {
                String userId=uo.getUserId();
                uo.setUserInfo(userInfoDao.getById(userId));
            }
            openPage.setRows(list);
        }else{
            openPage.setTotal(count);
            openPage.setRows(new ArrayList<UserOrder>());

        }
        return openPage;

    }

    @Override
	public void updateOrderByOrderNo(String orderNo, String orderStatus) {
		   StringBuilder sql = new StringBuilder("update t_airrent_user_order set ");
	        sql.append(" order_status='"+orderStatus+"'");
	        sql.append(" where order_number='"+orderNo+"'");
	        getJdbcTemplate().update(sql.toString());
		
	}

	@Override
	public UserOrder findOrderByOrderNo(String orderNo) {
		   StringBuilder sql = new StringBuilder("select * from   t_airrent_user_order  ");
	        sql.append(" where  order_number='"+orderNo+"'");
	        List<UserOrder> orderList = getJdbcTemplate().query(sql.toString(),new UserOrderMapper());
	        if(CollectionUtils.isEmpty(orderList)){
	        	return null;
	        }
	        return orderList.get(0);
	}

	@Override
	public String updateOrderStatus(String orderId, String orderStatus) {
		StringBuilder sql = new StringBuilder("update t_airrent_user_order set ");
        sql.append(" order_status='"+orderStatus+"'");
        sql.append(" where order_id='"+orderId+"'");
        int update=getJdbcTemplate().update(sql.toString());
        if (update>0) {
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}



}
