package com.eighth.airrent.proxy.service.impl;

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
}
