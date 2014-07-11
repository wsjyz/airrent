package com.eighth.airrent.controller;

import com.eighth.airrent.domain.VIPCard;
import com.eighth.airrent.proxy.service.VIPCardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/VIPCardService")
public class VIPCardController{

	@Autowired
	VIPCardService VIPCardService;
	/**
	 *已测试
	 * @return
	 */
	@RequestMapping(value = "/findVIPCardList")
	@ResponseBody
	public List<VIPCard> findVIPCardList() {
		return VIPCardService.findVIPCardList();
	}


}
