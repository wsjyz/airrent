package com.eighth.airrent.dao;

import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

import java.util.List;

/**
 * Created by dam on 2014/7/2.
 */
public interface UserCollectionDAO {

    /**
     * 获取用户收藏的机场/飞机列表
     * @param userId
     * @param collectionType PLANE飞机 AIRPORT机场
     * @return
     */
    List<UserCollection> findUserCollection(String userId,String collectionType);

    /**
     * 收藏机场或者飞机
     * @param collection
     * @return SUCCESS成功，FAIL失败
     * @throws RemoteInvokeException
     */
    String addUserCollection(UserCollection collection);
}
