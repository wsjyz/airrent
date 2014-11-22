package com.eighth.airrent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.eighth.airrent.dao.AirlineDAO;
import com.eighth.airrent.dao.PlaneDAO;
import com.eighth.airrent.dao.UserCollectionDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;
import com.eighth.airrent.proxy.service.UserCollectionService;

@Service("AirlineService")
public class AirlineServiceImpl implements AirlineService{

	@Autowired
	private AirlineDAO airlineDAO;
	@Autowired
	UserCollectionDAO userCollectionDAO;
	@Autowired
	PlaneDAO  planeDAO;
	@Override
	public Airline findAirlineById(String airlineId)
			throws RemoteInvokeException {
		return airlineDAO.findAirlineById(airlineId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,
			String airlineId) throws RemoteInvokeException {
		openPage= airlineDAO.findPlaneByAirlineId(openPage, airlineId);
		if (!CollectionUtils.isEmpty(openPage.getRows())) {
			for (Object obj : openPage.getRows()) {
				Plane plane=(Plane)obj;
                int count= userCollectionDAO.getUserCollectionCount(plane.getPlaneId(),"PLANE");
                plane.setCollectionCount(count);
                int countByPlane = planeDAO.getCountByPlane(plane.getPlaneId());
                plane.setClickCount(countByPlane);
			}
		}
		return openPage;
	}

	@Override
	public List<Airline> findAirlineAllById(String airportId,String address)
			throws RemoteInvokeException {
		return airlineDAO.findAirlineAllById(airportId,address);
	}

	@Override
	public String addAirline(Airline airline) throws RemoteInvokeException {
		return airlineDAO.addAirline(airline);
	}

	@Override
	public String updateAirline(Airline airline) throws RemoteInvokeException {
		return airlineDAO.updateAirline(airline);
	}

	@Override
	public String deleteAirline(String airlineId) throws RemoteInvokeException {
		return airlineDAO.deleteAirline(airlineId);
	}

    @Override
    public OpenPage findAirlineList(OpenPage page, String airlineName, String loginName) {

        return airlineDAO.findAirlineList(page, airlineName, loginName);
    }

    @Override
    public String saveAirline(Airline airline) {
        return airlineDAO.saveAirline(airline);
    }

    @Override
    public Airline loginAirline(String loginName, String password) {
        return airlineDAO.finAirline(loginName,password);
    }

    @Override
	public List<Plane> findAllPlaneByAirlineId(String airlineId) {
		return airlineDAO.findAllPlaneByAirlineId(airlineId);
	}

    @Override
    public String resetPassword(String airlineId,String newPassword){return airlineDAO.resetPassword(airlineId,newPassword);}
}
