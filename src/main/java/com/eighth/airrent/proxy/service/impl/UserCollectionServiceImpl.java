package com.eighth.airrent.proxy.service.impl;

import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserCollectionService;

import java.util.List;

/**
 * Created by dam on 2014/7/2.
 */
public class UserCollectionServiceImpl implements UserCollectionService {
    @Override
    public List<UserCollection> findUserCollection(String userId, String collectionType) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String addUserCollection(UserCollection collection) throws RemoteInvokeException {
        return null;
    }

	@Override
	public boolean checkUserCollection(String userId, String objId,
			String collectionType) {
		return false;
	}

	@Override
	public boolean deleteUserCollection(String userId, String objId,
			String collectionType) {
		// TODO Auto-generated method stub
		return false;
	}
}
