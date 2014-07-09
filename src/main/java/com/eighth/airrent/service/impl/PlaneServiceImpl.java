package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.PlaneService;

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

}