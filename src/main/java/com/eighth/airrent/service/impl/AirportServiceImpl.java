package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirportService;


/**
 * Created by dam on 2014/7/2.
 */
@Service("AirportService")
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportDAO airportDAO;

	@Override
	public OpenPage<Airport> findAirportList(OpenPage openPage,String airportName)
			throws RemoteInvokeException {
		return airportDAO.findAirportList(openPage, airportName);
	}

	@Override
	public Airport findAirportById(String airportId)
			throws RemoteInvokeException {
		return airportDAO.findAirportById(airportId);
	}

	@Override
	public OpenPage<Plane> findPlaneByAirportId(OpenPage openPage,
			String airportId) throws RemoteInvokeException {
		return airportDAO.findPlaneByAirportId(openPage, airportId);
	}

}
