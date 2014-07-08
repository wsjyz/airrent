package com.eighth.airrent.controller;

import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserCollectionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dam on 2014/7/2.
 */
@Controller
@RequestMapping(value = "/UserCollectionService")
public class UserCollectionController {

	@Autowired
	UserCollectionService UserCollectionService;
	
	@RequestMapping(value = "/findUserCollection")
	@ResponseBody	
	public List<UserCollection> findUserCollection(@RequestParam String userId,
			@RequestParam String collectionType) throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/addUserCollection")
	@ResponseBody
	public String addUserCollection(@RequestParam UserCollection collection)
			throws RemoteInvokeException {
		// TODO Auto-generated method stub
		return null;
	}

}
