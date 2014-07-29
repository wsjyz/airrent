package com.eighth.airrent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.airrent.dao.AirportDAO;
import com.eighth.airrent.dao.CorpDAO;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.Corp;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.CorpService;

/**
 * Created by dam on 2014/7/2.
 */
@Service("CorpService")
public class CorpServiceImpl implements CorpService {

	@Autowired
	CorpDAO  corpDao;
	@Autowired
	AirportDAO airportDao;
	@Override
	public String addCorp(Corp corp) throws RemoteInvokeException {
		return corpDao.addCorp(corp);
	}

	@Override
	public OpenPage<Corp> getCorpPage(OpenPage openPage)
			throws RemoteInvokeException {
		openPage=corpDao.getCorpPage(openPage);
		if (openPage!=null && openPage.getTotal()>0) {
			for (Object obj : openPage.getRows()) {
				Corp corp=(Corp)obj;
				Airport airport = airportDao.findAirportById(corp.getAirportId());
				corp.setAirport(airport);
			}
		}
		return openPage;
	}

	@Override
	public Corp getCorpById(String corpId) throws RemoteInvokeException {
	
		return corpDao.getCorpById(corpId);
	}

	@Override
	public String updateCorp(Corp corp) throws RemoteInvokeException {
		return corpDao.updateCorp(corp);
	}

	@Override
	public String deleteCorp(String corpId) throws RemoteInvokeException {
		return corpDao.deleteCorp(corpId);
	}
  
}
