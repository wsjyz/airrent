package com.eighth.airrent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.ApkVersionService;

/**
 * Created by dam on 2014/6/30.
 */
@Controller
@RequestMapping(value = "/ApkVersionService")
public class ApkVersionController {
	@Autowired
	ApkVersionService apkVersionService;

	@RequestMapping(value = "/getApkVersion")
	@ResponseBody
	public APKVersion getApkVersion()
			throws RemoteInvokeException {
		return apkVersionService.getApkVersion();
	}
}
