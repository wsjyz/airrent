package com.eighth.airrent.dao;

import com.eighth.airrent.domain.Airline;

public interface AirlineDAO {
	
	Airline findAirlineById(String airlineId);

}
