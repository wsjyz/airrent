package com.eighth.airrent.controller;

import com.eighth.airrent.domain.UserCollection;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserCollectionService;
import com.eighth.airrent.web.FastJson;

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
	/**
	 * 已测试
	 * @param userId
	 * @param collectionType
	 * @return
	 * @throws RemoteInvokeException
	 */
	@RequestMapping(value = "/findUserCollection")
	@ResponseBody	
	public List<UserCollection> findUserCollection(@RequestParam String userId,
			@RequestParam String collectionType) throws RemoteInvokeException {
		return UserCollectionService.findUserCollection(userId, collectionType);
	}
/**
 * 已测试
 * @param collection
 * @return
 * @throws RemoteInvokeException
 */
	@RequestMapping(value = "/addUserCollection")
	@ResponseBody
	public String addUserCollection(@FastJson UserCollection collection)
			throws RemoteInvokeException {
		return UserCollectionService.addUserCollection(collection);
	}
	@RequestMapping(value = "/checkUserCollection")
	@ResponseBody	
	public boolean checkUserCollection(@RequestParam String userId,@RequestParam String objId,
			@RequestParam String collectionType) throws RemoteInvokeException {
		return UserCollectionService.checkUserCollection(userId,objId,collectionType);
	}
	
	@RequestMapping(value = "/deleteUserCollection")
	@ResponseBody	
	public boolean deleteUserCollection(@RequestParam String userId,@RequestParam String objId,
			@RequestParam String collectionType) throws RemoteInvokeException {
		return UserCollectionService.deleteUserCollection(userId,objId,collectionType);
	}
}
