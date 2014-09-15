package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.UserCollectionDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.UserCollection;

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
			sql.append("select auc.user_id,auc.plane_id,ap.plane_name from t_airrent_user_collection auc left join t_airrent_plane ap on auc.plane_id=ap.plane_id and (auc.airline_id is null or auc.airline_id ='' or auc.airline_id='null')");
		} else {
			sql.append("select auc.user_id,auc.airline_id,aa.airline_name from t_airrent_user_collection auc left join t_airrent_airline aa on auc.airline_id=aa.airline_id and (auc.plane_id is null or auc.plane_id ='' or auc.plane_id='null')");
		}
		sql.append(" where auc.user_id='" + userId + "' ");
		if ("PLANE".equals(collectionType)) {
			sql.append(" and (auc.airline_id is null or auc.airline_id ='' or auc.airline_id='null')");
		} else {
			sql.append(" and (auc.plane_id is null or auc.plane_id ='' or auc.plane_id='null')");
		}
		List<UserCollection> list = new ArrayList<UserCollection>();
		if ("PLANE".equals(collectionType)) {
			list = getJdbcTemplate().query(sql.toString(),
					new UserPlaneCollectionMapper());
		} else {
			list = getJdbcTemplate().query(sql.toString(),
					new UserAirlineCollectionMapper());
		}
		return list;
	}
	 public class UserAirlineCollectionMapper implements RowMapper<UserCollection>{
		 @Override
			public UserCollection mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				UserCollection userCollection = new UserCollection();
				userCollection.setAirlineId(rs
						.getString("airline_id"));
				userCollection.setAirlineName(rs
						.getString("airline_name"));
				userCollection.setUserId(rs.getString("user_id"));
				return userCollection;
			}
	    }
	 public class UserPlaneCollectionMapper implements RowMapper<UserCollection>{
		 @Override
			public UserCollection mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				UserCollection userCollection = new UserCollection();
				userCollection.setPlaneId(rs
						.getString("plane_id"));
				userCollection.setPlaneName(rs
						.getString("plane_name"));
				userCollection.setUserId(rs.getString("user_id"));
				return userCollection;
			}
	    }
	@Override
	public String addUserCollection(UserCollection collection) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT into t_airrent_user_collection(user_id,plane_id,airline_id) values('"
				+ collection.getUserId()
				+ "','"
				+ collection.getPlaneId()
				+ "','" + collection.getAirlineId() + "')");
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}
	@Override
	public boolean checkUserCollection(String userId, String objId,
			String collectionType) {
		StringBuffer sql = new StringBuffer();
			sql.append("select * from t_airrent_user_collection where  user_id='" + userId + "' ");
		if("PLANE".equals(collectionType)){
			sql.append(" and plane_id='"+objId+"'");
		}else{
			sql.append(" and airline_id='"+objId+"'");
		}
		List<UserCollection> list = new ArrayList<UserCollection>();
		if ("PLANE".equals(collectionType)) {
			list = getJdbcTemplate().query(sql.toString(),
					new UserPlaneCollectionMapper());
		} else {
			list = getJdbcTemplate().query(sql.toString(),
					new UserCollectionMapper());
		}
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}else{
			return true;
		}
	}
	 public class UserCollectionMapper implements RowMapper<UserCollection>{
		 @Override
			public UserCollection mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				UserCollection userCollection = new UserCollection();
				userCollection.setPlaneId(rs
						.getString("plane_id"));
				userCollection.setUserId(rs.getString("user_id"));
				return userCollection;
			}
	    }
	@Override
	public boolean deleteUserCollection(String userId, String objId,
			String collectionType) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete  from t_airrent_user_collection where  user_id='" + userId + "' ");
		if("PLANE".equals(collectionType)){
			sql.append(" and plane_id='"+objId+"'");
		}else{
			sql.append(" and airline_id='"+objId+"'");
		}
		int update = getJdbcTemplate().update(sql.toString());
		if (update>0) {
			return true;
		}
		return false;
	}
}
