package com.eighth.airrent.proxy.service.impl;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.PlaneService;

/**
 * Created by dam on 2014/7/2.
 */
public class PlaneServiceImpl implements PlaneService {
    @Override
    public Plane findPlaneById(String planeId) throws RemoteInvokeException {
        return null;
    }

	@Override
	public String addPlane(Plane plane) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePlane(String planeId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePlane(Plane plane) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public OpenPage findPlaneList(OpenPage page, Plane plane) {
        return null;
    }

    @Override
    public String savePlane(Plane plane) {
        return null;
    }

    @Override
    public String updatePlaneStatus(Plane plane) {
        return null;
    }

	@Override
	public boolean saveClickByPlane(String planeId, String userId)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return false;
	}
}
