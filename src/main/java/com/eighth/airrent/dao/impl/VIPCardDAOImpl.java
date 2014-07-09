package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.VIPCardDAO;
import com.eighth.airrent.domain.VIPCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "VIPCardDAO")
public class VIPCardDAOImpl  extends BaseDAO implements VIPCardDAO{

	@Override
	public List<VIPCard> findVIPCardList() {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_vip_card ");
		List<VIPCard> list = getJdbcTemplate().query(sql.toString(), new RowMapper<VIPCard>() {
			@Override
			public VIPCard mapRow(ResultSet rs, int rowNum) throws SQLException {
				VIPCard VIPCard = new VIPCard();
				VIPCard.setCardId(rs.getString("card_id"));
				VIPCard.setCardName(rs.getString("card_name"));
				VIPCard.setCardPrice(rs.getBigDecimal("card_price"));
				return VIPCard;
			}
		});
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<VIPCard>();
		}
		return list;
	}

}
