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
import com.eighth.airrent.dao.OrderPayRelDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.OrderPayRel;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.util.CommonUtils;

@Repository(value = "OrderPayRelDAO")
public class OrderPayRelDAOImpl extends BaseDAO implements OrderPayRelDAO {

	@Override
	public OrderPayRel findOrderByOrderNum(String orderNum) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_airrent_order_pay_rel where order_num='"
				+ orderNum + "'");
		List<OrderPayRel> list = getJdbcTemplate().query(sql.toString(),
				new OrderPayRelMapper());
		OrderPayRel orderPayRel=new OrderPayRel();
		if (CollectionUtils.isEmpty(list)) {
			return orderPayRel;
		}else{
			orderPayRel=list.get(0);
			
		}
		return orderPayRel;
	}

	public class OrderPayRelMapper implements RowMapper<OrderPayRel> {
		@Override
		public OrderPayRel mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderPayRel orderPayRel = new OrderPayRel();
			orderPayRel.setId(rs.getString("id"));
			orderPayRel.setUserId(rs.getString("user_id"));
			orderPayRel.setCard(rs.getString("card"));
			orderPayRel.setOrderNum(rs.getString("order_num"));
			return orderPayRel;
		}

	}

	@Override
	public String save(OrderPayRel OrderPayRel) {
		StringBuffer sql = new StringBuffer();
		String id = CommonUtils.genUUID();
		sql.append("INSERT into t_airrent_order_pay_rel(id,user_id,card,order_num) values('"
				+ id
				+ "','"
				+ OrderPayRel.getUserId()
				+ "','"
				+ OrderPayRel.getCard()
				+ "','"
				+ OrderPayRel.getOrderNum()
				 + "')");
		int update = getJdbcTemplate().update(sql.toString());
		String status = "FAIL";
		if (update > 0) {
			status = "SUCCESS";
		}
		return status;
	}
}
