package com.eighth.airrent.service.impl;

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

}
