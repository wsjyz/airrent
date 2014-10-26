package com.eighth.airrent.dao.impl;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eighth.airrent.dao.UserDAO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserOrderDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Airport;
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
//		String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
//		//返回格式
//		String format = "xml";
//		//必填，不需要修改
//		
//		//返回格式
//		String v = "2.0";
//		//必填，不需要修改
//		
//		//请求号
//		String req_id = UtilDate.getOrderNum();
//		//必填，须保证每次请求都是唯一
//		
//		//req_data详细信息
//		
//		//服务器异步通知页面路径
//		String notify_url = "http://localhost:8080/airrent/notify_url.jsp";
//		//需http://格式的完整路径，不能加?id=123这类自定义参数
//
//		//页面跳转同步通知页面路径
//		String call_back_url = "http://localhost:8080/airrent/call_back_url.jsp";
//		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
//
//		//操作中断返回地址
//		String merchant_url = "http://localhost:8080/airrent/xxxxxx.jsp";
//		//用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
//
//		//卖家支付宝帐户
//		String seller_email = new String();
//		//必填
//
//		//商户订单号
//		String out_trade_no = new String(userOrder.getOrderId());
//		//商户网站订单系统中唯一订单号，必填
//
//		//订单名称
//		String subject = new String();
//		//必填
//
//		//付款金额
//		String total_fee = new String(userOrder.getDownPayment()+"");
//		//必填
//		
//		//请求业务参数详细
//		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
//		//必填
//		
//		//////////////////////////////////////////////////////////////////////////////////
//		
//		//把请求参数打包成数组
//		Map<String, String> sParaTempToken = new HashMap<String, String>();
//		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
//		sParaTempToken.put("partner", AlipayConfig.partner);
//		sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
//		sParaTempToken.put("sec_id", AlipayConfig.sign_type);
//		sParaTempToken.put("format", format);
//		sParaTempToken.put("v", v);
//		sParaTempToken.put("req_id", req_id);
//		sParaTempToken.put("req_data", req_dataToken);
//		
//		//建立请求
//		String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
//		//URLDECODE返回的信息
//		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
//		//获取token
//		String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
//		//out.println(request_token);
//		
//		////////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////
//		
//		//业务详细
//		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
//		//必填
//		
//		//把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
//		sParaTemp.put("partner", AlipayConfig.partner);
//		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//		sParaTemp.put("sec_id", AlipayConfig.sign_type);
//		sParaTemp.put("format", format);
//		sParaTemp.put("v", v);
//		sParaTemp.put("req_data", req_data);
//		
//		//建立请求
//		String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
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
				+ order.getOrderLetter()+ "','" + UtilDate.getOrderNum()+ "','"
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
