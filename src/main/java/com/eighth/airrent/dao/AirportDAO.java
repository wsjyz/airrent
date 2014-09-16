package com.eighth.airrent.dao;

import java.util.List;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;

/**
 * Created by dam on 14-6-25.
 */
public interface AirportDAO {

	OpenPage<Airport> findAirportList(OpenPage<Airport> openPage,
			String airportName);

	Airport findAirportById(String airportId);

	OpenPage<Plane> findPlaneByAirportId(OpenPage openPage, String airportId);

	String addAirport(Airport airport);

	String deleteAirprot(String airprotId);
	/**
	 * 根据地址魔火查询所有机场
	 * @param address
	 * @return
	 */
	
	List<Airport> findAllAirport(String address);

    OpenPage<Airport> findAirportList(OpenPage<Airport> page, String airportName, String address);

    String saveAirport(Airport airport);

    List<Airport> findAllAirport();
}
