package com.eighth.airrent.controller;

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
	@RequestMapping("/payment")
    public ModelAndView toPay() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }
}
