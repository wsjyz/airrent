package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserCollectionDAO;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

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
@Repository(value = "UserCollectionDAO")
public class UserCollectionDAOImpl extends BaseDAO implements UserCollectionDAO {

	@Override
	public List<UserCollection> findUserCollection(String userId,
			String collectionType) {
		StringBuffer sql = new StringBuffer();
		if ("PLANE".equals(collectionType)) {
			sql.append("select auc.user_id,auc.plane_id,ap.plane_name from t_airrent_user_collection auc left join t_airrent_plane ap on auc.plane_id=ap.plane_id ");
		} else {
			sql.append("select auc.user_id,auc.airport_id,aa.airport_name from t_airrent_user_collection auc left join t_airrent_airport aa on auc.airport_id=aa.airport_id ");
		}
		sql.append(" where auc.user_id='" + userId + "' ");
		List<UserCollection> list = new ArrayList<UserCollection>();
		if ("PLANE".equals(collectionType)) {
			list = getJdbcTemplate().query(sql.toString(),
					new RowMapper<UserCollection>() {
						@Override
						public UserCollection mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserCollection userCollection = new UserCollection();
							userCollection.setPlaneId(rs.getString("plane_id"));
							userCollection.setPlaneName(rs
									.getString("plane_name"));
							userCollection.setUserId(rs.getString("user_id"));
							return userCollection;
						}
					});
		} else {
			list = getJdbcTemplate().query(sql.toString(),
					new RowMapper<UserCollection>() {
						@Override
						public UserCollection mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserCollection userCollection = new UserCollection();
							userCollection.setAirportId(rs
									.getString("airport_id"));
							userCollection.setAirportName(rs
									.getString("airport_name"));
							userCollection.setUserId(rs.getString("user_id"));
							return userCollection;
						}
					});
		}
		return list;
	}

	@Override
	public String addUserCollection(UserCollection collection) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT into t_airrent_user_collection(user_id,plane_id,airport_id) values('"
				+ collection.getUserId()
				+ "','"
				+ collection.getPlaneId()
				+ "','" + collection.getAirportId() + "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}
}
