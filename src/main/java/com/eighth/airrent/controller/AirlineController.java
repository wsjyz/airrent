package com.eighth.airrent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;
import com.eighth.airrent.web.FastJson;

@Controller
@RequestMapping(value = "/AirlineService")
public class AirlineController {
	
	@Autowired
	AirlineService airlineService;
	
	/**
	 * 已测试
	 * @param airlineId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findAirlineById")
	@ResponseBody
	public Airline findAirlineById(@RequestParam String airlineId) throws RemoteInvokeException{
		Airline airline = airlineService.findAirlineById(airlineId);
		return airline;
		
	}
	/**
	 * 已测试
	 * @param openPage
	 * @param airlineId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findPlaneByAirlineId")
	@ResponseBody
	public OpenPage<Plane> findPlaneByAirlineId(@FastJson OpenPage openPage,
			@RequestParam String airlineId) throws RemoteInvokeException {
		
		return airlineService.findPlaneByAirlineId(openPage, airlineId);
	}
	@RequestMapping(value = "/findAirlineAllById")
	@ResponseBody
	public List<Airline> findAirlineAllById(@RequestParam String airportId,@RequestParam String address) throws RemoteInvokeException{
		 List<Airline> list = airlineService.findAirlineAllById(airportId,address);
		return list;
	}
	
	@RequestMapping(value = "/findAllPlaneByAirlineId")
	@ResponseBody
	public List<Plane> findPlaneByAirlineId(@RequestParam String airlineId) throws RemoteInvokeException {
		
		return airlineService.findPlaneByAirlineId(airlineId);
	}
	
	/**
	 * 新增公司
	 * 
	 * @param corp
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/addAirline")
	@ResponseBody
	public String addAirline(@FastJson Airline airline) throws RemoteInvokeException {
		return airlineService.addAirline(airline);

	}
	/**
	 * 修改公司基本信息
	 * 
	 * @param corp
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/updateAirline")
	@ResponseBody
	public String updateAirline(@FastJson Airline airline) throws RemoteInvokeException {
		return airlineService.updateAirline(airline);

	};

	/**
	 * 删除公司
	 * 
	 * @param corpId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/deleteAirline")
	@ResponseBody
	public String deleteAirline(@RequestParam String airlineId)
			throws RemoteInvokeException {
		return airlineService.deleteAirline(airlineId);
	};
}
