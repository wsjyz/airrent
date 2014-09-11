package com.eighth.airrent.proxy.service.impl;

import java.util.List;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;

/**
 * Created by dam on 2014/7/2.
 */
public class AirlineServiceImpl implements AirlineService {
    @Override
    public Airline findAirlineById(String airlineId) throws RemoteInvokeException {
        return null;
    }

    @Override
    public OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage, String airlineId) throws RemoteInvokeException {
        return null;
    }

	@Override
	public List<Airline> findAirlineAllById(String AirportId,String address)
			throws RemoteInvokeException {
		return null;
	}

	@Override
	public String addAirline(Airline airline) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAirline(Airline airline) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAirline(String airlineId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plane> findAllPlaneByAirlineId(String airlineId) {
		// TODO Auto-generated method stub
		return null;
	}
}
