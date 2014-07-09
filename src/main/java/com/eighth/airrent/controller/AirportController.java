package com.eighth.airrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirportService;


/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/AirportService")
public class AirportController {
	@Autowired
	AirportService airportService;

	@RequestMapping(value = "/findAirportList")
	@ResponseBody
	public OpenPage<Airport> findAirportList(@RequestParam String airportName)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/findAirportById")
	@ResponseBody
	public Airport findAirportById(@RequestParam String airportId)
			throws RemoteInvokeException {
		return airportService.findAirportById(airportId);
	}

	@RequestMapping(value = "/findPlaneByAirportId")
	@ResponseBody
	public OpenPage<Plane> findPlaneByAirportId(@RequestParam OpenPage openPage,
			@RequestParam  String airportId) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

}
