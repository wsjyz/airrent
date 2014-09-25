package com.eighth.airrent.service.impl;

import com.eighth.airrent.dao.AirlineDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.dao.UserCollectionDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserCollectionService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by dam on 2014/7/2.
 */
@Service("UserCollectionService")
public class UserCollectionServiceImpl implements UserCollectionService{
	@Autowired
	UserCollectionDAO userCollectionDAO;
    @Autowired
    PlaneDAO planeDAO;
    @Autowired
    AirlineDAO airlineDao;
	@Override
	public List<UserCollection> findUserCollection(String userId,
			String collectionType) throws RemoteInvokeException {
        List<UserCollection> list= userCollectionDAO.findUserCollection(userId, collectionType);

        if (!CollectionUtils.isEmpty(list)) {
            for (UserCollection userCollection : list) {
                if (StringUtils.isNotEmpty(userCollection.getPlaneId())){
                   int count= userCollectionDAO.getUserCollectionCount(userCollection.getPlaneId(),"PLANE");
                    Plane plane = planeDAO.findPlaneById(userCollection.getPlaneId());
                    Airline airline = airlineDao.findAirlineById(plane.getAirlineId());
                    userCollection.setPlanePicUrl(plane.getPlaneImage());
                    userCollection.setAirlinePicUrl(airline.getAirlineImage());
                    userCollection.setPlanCount(count);
                }
                if(StringUtils.isNotEmpty(userCollection.getAirlineId())){
                    int count= userCollectionDAO.getUserCollectionCount(userCollection.getAirlineId(),"AIRLINE");
                    Plane plane = planeDAO.findPlaneById(userCollection.getPlaneId());
                    Airline airline = airlineDao.findAirlineById(plane.getAirlineId());
                    userCollection.setPlanePicUrl(plane.getPlaneImage());
                    userCollection.setAirlinePicUrl(airline.getAirlineImage());
                    userCollection.setPlanCount(count);
                }
            }
        }
        return list;
    }

	@Override
	public String addUserCollection(UserCollection collection)
			throws RemoteInvokeException {
		return userCollectionDAO.addUserCollection(collection);
	}

	@Override
	public boolean checkUserCollection(String userId, String objId,
			String collectionType) {
		return userCollectionDAO.checkUserCollection(userId, objId, collectionType);
	}

	@Override
	public boolean deleteUserCollection(String userId, String objId,
			String collectionType) {
		return userCollectionDAO.deleteUserCollection(userId, objId, collectionType);
	}

}
