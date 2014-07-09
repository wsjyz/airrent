package com.eighth.airrent.service.impl;

import com.eighth.airrent.dao.InformationDAO;
import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.proxy.service.InfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dam on 2014/6/29.
 */
@Service("InfoService")
public class InfoServiceImpl implements InfoService{

	@Autowired
	InformationDAO InformationDAO;
	@Override
	public OpenPage<Information> getInformations(OpenPage openPage) {
		// TODO Auto-generated method stub
		return null;
	}

   
}
