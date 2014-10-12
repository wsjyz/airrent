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
import com.eighth.airrent.domain.Setting;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;
import com.eighth.airrent.proxy.service.SettingService;
import com.eighth.airrent.web.FastJson;

@Controller
@RequestMapping(value = "/SettingService")
public class SettingController {
	
	@Autowired
	SettingService settingService;
	@RequestMapping(value = "/findAirlineById")
	@ResponseBody
	public Setting loadSetting() throws RemoteInvokeException{
		Setting setting = settingService.loadSetting();
		return setting;
		
	}
	
}
