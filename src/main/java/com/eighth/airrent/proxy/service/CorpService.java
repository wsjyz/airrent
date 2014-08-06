package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.Corp;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface CorpService {

	/**
	 * 新增公司
	 * 
	 * @param corp
	 * @return
	 * @throws RemoteInvokeException
	 */
	String addCorp(Corp corp) throws RemoteInvokeException;

	/**
	 * 公司分页查询
	 * 
	 * @param openPage
	 * @return
	 * @throws RemoteInvokeException
	 */

	OpenPage<Corp> getCorpPage(OpenPage openPage) throws RemoteInvokeException;

	/**
	 * 根据公司ID查询
	 * 
	 * @param corpId
	 * @return
	 * @throws RemoteInvokeException
	 */
	Corp getCorpById(String corpId) throws RemoteInvokeException;

	/**
	 * 修改公司基本信息
	 * 
	 * @param corp
	 * @return
	 * @throws RemoteInvokeException
	 */
	String updateCorp(Corp corp) throws RemoteInvokeException;

	/**
	 * 删除公司
	 * 
	 * @param corpId
	 * @return
	 * @throws RemoteInvokeException
	 */
	String deleteCorp(String corpId) throws RemoteInvokeException;
}
