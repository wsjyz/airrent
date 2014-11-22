package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.ApkVersionDAO;
import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.ApkVersionService;

/**
 * Created by dam on 2014/7/2.
 */
@Service("ApkVersionService")
public class ApkVersionServiceImpl implements ApkVersionService {

	@Autowired
	private ApkVersionDAO apkVersionDAO;
	@Override
	public APKVersion getApkVersion() throws RemoteInvokeException {
		return apkVersionDAO.getApkVersion();
	}
  
}
