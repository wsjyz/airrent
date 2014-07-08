package com.eighth.airrent.dao;

import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface PlaneDAO {

    /**
     * 飞机详情
     * @param planeId
     * @return
     * @throws RemoteInvokeException
     */
    Plane findPlaneById(String planeId)throws RemoteInvokeException;
}
