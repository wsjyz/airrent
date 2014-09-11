package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface PlaneService {

	/**
	 * 飞机详情
	 * 
	 * @param planeId
	 * @return
	 * @throws RemoteInvokeException
	 */
    @RemoteMethod(methodVarNames={ "planeId"})
	Plane findPlaneById(String planeId) throws RemoteInvokeException;

	/**
	 * 新增飞机
	 * 
	 * @param plane
	 * @return
	 * @throws RemoteInvokeException
	 */
	String addPlane(Plane plane) throws RemoteInvokeException;

	/**
	 * 删除飞机
	 * 
	 * @param planeId
	 * @return
	 * @throws RemoteInvokeException
	 */
	String deletePlane(String planeId) throws RemoteInvokeException;

	/**
	 * 修改飞机基本信息 
	 * @param plane
	 * @return
	 * @throws RemoteInvokeException
	 */
	String updatePlane(Plane plane) throws RemoteInvokeException;

    OpenPage findPlaneList(OpenPage page, Plane plane);
}
