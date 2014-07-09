package com.eighth.airrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;

@Controller
@RequestMapping(value = "/AirlineService")
public class AirlineController {
	
	@Autowired
	AirlineService airlineService;
	
	
	@RequestMapping(value = "/findAirlineById")
	@ResponseBody
	public Airline findAirlineById(@RequestParam String airlineId) throws RemoteInvokeException{
		Airline airline = airlineService.findAirlineById(airlineId);
		return airline;
		
	}
	@RequestMapping(value = "/findPlaneByAirlineId")
	@ResponseBody
	public OpenPage<Plane> findPlaneByAirlineId(@RequestParam OpenPage openPage,
			@RequestParam String airlineId) throws RemoteInvokeException {
		return airlineService.findPlaneByAirlineId(openPage, airlineId);
	}
}
