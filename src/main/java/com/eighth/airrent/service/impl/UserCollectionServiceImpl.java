package com.eighth.airrent.service.impl;

import com.eighth.airrent.dao.UserCollectionDAO;
import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserCollectionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dam on 2014/7/2.
 */
@Service("UserCollectionService")
public class UserCollectionServiceImpl implements UserCollectionService{
	@Autowired
	UserCollectionDAO userCollectionDAO;
	@Override
	public List<UserCollection> findUserCollection(String userId,
			String collectionType) throws RemoteInvokeException {
		return userCollectionDAO.findUserCollection(userId, collectionType);
	}

	@Override
	public String addUserCollection(UserCollection collection)
			throws RemoteInvokeException {
		return userCollectionDAO.addUserCollection(collection);
	}

}
