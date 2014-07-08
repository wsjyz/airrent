package com.eighth.airrent.dao.impl;

import com.eighth.airrent.dao.BaseDAO;
import com.eighth.airrent.dao.InformationDAO;
import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.OpenPage;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Created by dam on 2014/6/29.
 */
@Repository(value = "InformationDAO")
public class InformationDAOImpl extends BaseDAO implements InformationDAO{

	@Override
	public OpenPage<Information> getInformations(OpenPage openPage) {
		// TODO Auto-generated method stub
		return null;
	}

   
}
