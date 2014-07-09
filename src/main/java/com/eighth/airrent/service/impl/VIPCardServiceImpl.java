package com.eighth.airrent.service.impl;

import com.eighth.airrent.dao.VIPCardDAO;
import com.eighth.airrent.domain.VIPCard;
import com.eighth.airrent.proxy.service.VIPCardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dam on 2014/7/2.
 */
@Service("VIPCardService")
public class VIPCardServiceImpl implements VIPCardService{
	@Autowired
	VIPCardDAO VIPCardDAO;
	@Override
	public List<VIPCard> findVIPCardList() {
		return VIPCardDAO.findVIPCardList();
	}


}
