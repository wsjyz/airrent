package com.eighth.airrent.dao;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface PlaneDAO {

	/**
	 * 飞机详情
	 * 
	 * @param planeId
	 * @return
	 */
	Plane findPlaneById(String planeId);

	String addPlane(Plane plane);

	String deletePlane(String planeId);

	String updatePlane(Plane plane);

    OpenPage findPlaneList(OpenPage page, Plane plane);

    String savePlane(Plane plane);

    String updatePlaneStatus(Plane plane);
    
    int getCountByPlane(String planeId);
    
    boolean checkByClickPlane(String userId,String planeId);
     void saveClickPlane(String userId,String planeId);
}
