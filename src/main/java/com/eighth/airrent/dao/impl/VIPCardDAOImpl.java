package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.VIPCardDAO;
import com.eighth.airrent.domain.VIPCard;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Created by dam on 2014/7/2.
 */
@Repository(value = "VIPCardDAO")
public class VIPCardDAOImpl  extends BaseDAO implements VIPCardDAO{

	@Override
	public List<VIPCard> findVIPCardList() {
		// TODO Auto-generated method stub
		return null;
	}

}
