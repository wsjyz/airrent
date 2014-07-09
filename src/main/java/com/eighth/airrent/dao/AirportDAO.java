package com.eighth.airrent.dao;

import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;

/**
 * Created by dam on 14-6-25.
 */
public interface AirportDAO {
	
	  OpenPage<Airport> findAirportList(OpenPage<Airport> openPage,String airportName);
	 
	  Airport findAirportById(String airportId);

	   
	 OpenPage<Plane> findPlaneByAirportId(OpenPage openPage,String airportId);
}
