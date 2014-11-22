package com.eighth.airrent.service.impl;

import java.util.List;

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
	public OpenPage<Airport> findAirportList(OpenPage openPage,
			String airportName) throws RemoteInvokeException {
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

	@Override
	public String addAirport(Airport airport) throws RemoteInvokeException {
		return airportDAO.addAirport(airport);
	}

	@Override
	public String deleteAirprot(String airprotId) throws RemoteInvokeException {
		return airportDAO.deleteAirprot(airprotId);
	}

	@Override
	public List<Airport> findAllAirport(String address)
			throws RemoteInvokeException {
		return airportDAO.findAllAirport(address);
	}

    @Override
    public OpenPage<Airport> findAirportList(OpenPage<Airport> page, String airportName, String address) {
        return airportDAO.findAirportList(page,airportName,address);
    }

    @Override
    public String saveAirport(Airport airport) {
        return airportDAO.saveAirport(airport);
    }

    @Override
    public List<Airport> findAllAirport(){
        return airportDAO.findAllAirport();
    }

}
