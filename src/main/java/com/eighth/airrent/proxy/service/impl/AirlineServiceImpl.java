package com.eighth.airrent.proxy.service.impl;

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
}
