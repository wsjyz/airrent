package com.eighth.airrent.controller;

import java.util.List;

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
import com.eighth.airrent.web.FastJson;


/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/AirportService")
public class AirportController {
	@Autowired
	AirportService airportService;
	/**
	 * 已测试
	 * @param openPage
	 * @param airportName
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findAirportList")
	@ResponseBody
	public OpenPage<Airport> findAirportList(@FastJson OpenPage openPage,@RequestParam String airportName)
			throws RemoteInvokeException {
		return airportService.findAirportList(openPage,airportName);
	}

	/**
	 * 已测试
	 * @param airportId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findAirportById")
	@ResponseBody
	public Airport findAirportById(@RequestParam String airportId)
			throws RemoteInvokeException {
		return airportService.findAirportById(airportId);
	}

	/**
	 * 已测试
	 * @param openPage
	 * @param airportId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findPlaneByAirportId")
	@ResponseBody
	public OpenPage<Plane> findPlaneByAirportId(@FastJson OpenPage openPage,
			@RequestParam  String airportId) throws RemoteInvokeException {
		return airportService.findPlaneByAirportId(openPage, airportId);
	}
	/**
	 * 
	 * @param airport
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/addAirport")
	@ResponseBody
	public String addAirport(@FastJson Airport airport) throws RemoteInvokeException {
		return airportService.addAirport(airport);
	}
	/**
	 * 
	 * @param airprotId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/deleteAirprot")
	@ResponseBody
	public String deleteAirprot(String airprotId) throws RemoteInvokeException {
		return airportService.deleteAirprot(airprotId);
	}
	/**
	 * 
	 * @param airprotId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findAllAirport")
	@ResponseBody
	public List<Airport> findAllAirport(String address) throws RemoteInvokeException {
		return airportService.findAllAirport(address);
	}
}
