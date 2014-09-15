package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

import java.util.List;

/**
 * Created by dam on 2014/7/2.
 */
public interface UserCollectionService {

    /**
     * 获取用户收藏的机场/飞机列表
     * @param userId
     * @param collectionType PLANE飞机 AIRLINE机场
     * @return
     */
    @RemoteMethod(methodVarNames={ "userId","collectionType"})
    List<UserCollection> findUserCollection(String userId,String collectionType)throws RemoteInvokeException;

    /**
     * 收藏机场或者飞机
     * @param collection
     * @return SUCCESS成功，FAIL失败
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "collection"})
    String addUserCollection(UserCollection collection)throws RemoteInvokeException;
    @RemoteMethod(methodVarNames={ "userId","objId","collectionType"})
	boolean checkUserCollection(String userId, String objId,
			String collectionType);
    @RemoteMethod(methodVarNames={ "userId","objId","collectionType"})
	boolean deleteUserCollection(String userId, String objId,
			String collectionType);
}
