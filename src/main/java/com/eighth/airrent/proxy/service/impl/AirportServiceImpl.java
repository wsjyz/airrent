package com.eighth.airrent.proxy.service.impl;

import java.util.List;

import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirportService;

/**
 * Created by dam on 2014/7/2.
 */
public class AirportServiceImpl implements AirportService {
    @Override
    public OpenPage<Airport> findAirportList(OpenPage openPage,String airportName) throws RemoteInvokeException {
        return null;
    }

    @Override
    public Airport findAirportById(String airportId) throws RemoteInvokeException {
        return null;
    }

    @Override
    public OpenPage<Plane> findPlaneByAirportId(OpenPage openPage, String airportId) throws RemoteInvokeException {
        return null;
    }

	@Override
	public String addAirport(Airport airport) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAirprot(String airprotId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Airport> findAllAirport(String address)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public OpenPage<Airport> findAirportList(OpenPage<Airport> page, String airportName, String address) {
        return null;
    }

    @Override
    public String saveAirport(Airport airport) {
        return null;
    }

    @Override
    public List<Airport> findAllAirport() {
        return null;
    }
}
