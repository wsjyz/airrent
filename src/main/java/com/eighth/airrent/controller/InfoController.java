package com.eighth.airrent.controller;

import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.proxy.service.InfoService;
import com.eighth.airrent.web.FastJson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dam on 2014/6/29.
 */
@Controller
@RequestMapping(value = "/InfoService")
public class InfoController {
	@Autowired
	InfoService InfoService;

	/**
	 * 已测试
	 * @param openPage
	 * @return
	 */
	@RequestMapping(value = "/getInformations")
	@ResponseBody
	public OpenPage<Information> getInformations(@FastJson OpenPage openPage) {
		return InfoService.getInformations(openPage);
	}

}
