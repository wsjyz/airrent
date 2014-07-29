package com.eighth.airrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Corp;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;
import com.eighth.airrent.proxy.service.CorpService;
import com.eighth.airrent.web.FastJson;

@Controller
@RequestMapping(value = "/CorpService")
public class CorpController {

	@Autowired
	CorpService corpService;

	/**
	 * 新增公司
	 * 
	 * @param corp
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/addCorp")
	@ResponseBody
	public String addCorp(@FastJson Corp corp) throws RemoteInvokeException {
		return corpService.addCorp(corp);

	}

	/**
	 * 公司分页查询
	 * 
	 * @param openPage
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/getCorpPage")
	@ResponseBody
	public OpenPage<Corp> getCorpPage(@FastJson OpenPage openPage)
			throws RemoteInvokeException {
		return corpService.getCorpPage(openPage);

	}

	/**
	 * 根据公司ID查询
	 * 
	 * @param corpId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/getCorpById")
	@ResponseBody
	public Corp getCorpById(@RequestParam String corpId)
			throws RemoteInvokeException {
		return corpService.getCorpById(corpId);

	}

	/**
	 * 修改公司基本信息
	 * 
	 * @param corp
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/updateCorp")
	@ResponseBody
	public String updateCorp(@FastJson Corp corp) throws RemoteInvokeException {
		return corpService.updateCorp(corp);

	};

	/**
	 * 删除公司
	 * 
	 * @param corpId
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/deleteCorp")
	@ResponseBody
	public String deleteCorp(@RequestParam String corpId)
			throws RemoteInvokeException {
		return corpService.deleteCorp(corpId);
	};
}
