package com.eighth.airrent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.AirlineDAO;
import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;

@Service("AirlineService")
public class AirlineServiceImpl implements AirlineService{

	@Autowired
	private AirlineDAO airlineDAO;
	@Override
	public Airline findAirlineById(String airlineId)
			throws RemoteInvokeException {
		return airlineDAO.findAirlineById(airlineId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,
			String airlineId) throws RemoteInvokeException {
		return airlineDAO.findPlaneByAirlineId(openPage, airlineId);
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
	public List<Plane> findPlaneByAirlineId(String airlineId) {
		return airlineDAO.findPlaneByAirlineId(airlineId);

	}

}
