package com.eighth.airrent.service.impl;

import com.eighth.airrent.domain.OpenPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.PlaneService;
import com.eighth.airrent.proxy.service.UserCollectionService;

/**
 * Created by dam on 2014/7/2.
 */
@Service("PlaneService")
public class PlaneServiceImpl implements PlaneService {
	@Autowired
	PlaneDAO planeDAO;

	@Override
	public Plane findPlaneById(String planeId) throws RemoteInvokeException {
		return planeDAO.findPlaneById(planeId);
	}

	@Override
	public String addPlane(Plane plane) throws RemoteInvokeException {
		return planeDAO.addPlane(plane);
	}

	@Override
	public String deletePlane(String planeId) throws RemoteInvokeException {
		return planeDAO.deletePlane(planeId);
	}

	@Override
	public String updatePlane(Plane plane) throws RemoteInvokeException {
		return planeDAO.updatePlane(plane);
	}

    @Override
    public OpenPage findPlaneList(OpenPage page, Plane plane) {
        return planeDAO.findPlaneList(page, plane);
    }

    @Override
    public String savePlane(Plane plane) {
        return planeDAO.savePlane(plane);
    }

    @Override
    public String updatePlaneStatus(Plane plane) {
        return planeDAO.updatePlaneStatus(plane);
    }

	@Override
	public boolean saveClickByPlane(String planeId, String userId)
			throws RemoteInvokeException {
		boolean check = planeDAO.checkByClickPlane(userId, planeId);
		if (check) {
			planeDAO.saveClickPlane(userId, planeId);
		}
		return check;
	}

}
