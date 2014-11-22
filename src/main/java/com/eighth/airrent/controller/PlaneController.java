package com.eighth.airrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.PlaneService;
import com.eighth.airrent.web.FastJson;

/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/PlaneService")
public class PlaneController {

	@Autowired
	PlaneService planeService;

	/**
	 * 已测试
	 * 
	 * @param planeId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findPlaneById")
	@ResponseBody
	public Plane findPlaneById(@RequestParam String planeId)
			throws RemoteInvokeException {
		return planeService.findPlaneById(planeId);
	}

	@RequestMapping(value = "/addPlane")
	@ResponseBody
	public String addPlane(@FastJson Plane plane) throws RemoteInvokeException {
		return planeService.addPlane(plane);
	}

	@RequestMapping(value = "/deletePlane")
	@ResponseBody
	public String deletePlane(@RequestParam String planeId)
			throws RemoteInvokeException {
		return planeService.deletePlane(planeId);
	}
	@RequestMapping(value = "/updatePlane")
	@ResponseBody
	public String updatePlane(@FastJson Plane plane) throws RemoteInvokeException {
		return planeService.addPlane(plane);
	}
	@RequestMapping(value = "/saveClickByPlane")
	@ResponseBody
	public boolean saveClickByPlane(@RequestParam String planeId,@RequestParam String userId) throws RemoteInvokeException {
		return planeService.saveClickByPlane(planeId, userId);
	}
}
