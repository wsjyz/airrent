package com.eighth.airrent.proxy.service;

import java.util.List;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.annotation.RemoteMethod;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

/**
 * Created by dam on 2014/7/2.
 */
public interface AirlineService {

    /**
     * 航空公司详情
     * @param airlineId
     * @return
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "airlineId"})
    Airline findAirlineById(String airlineId)throws RemoteInvokeException;

    /**
     * 查找航空公司的飞机
     * @param openPage json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号 pageSize每页显示条数
     * @param airlineId
     * @return
     * @throws RemoteInvokeException
     */
    @RemoteMethod(methodVarNames={ "openPage","airlineId"})
    OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,String airlineId)throws RemoteInvokeException;
    
    @RemoteMethod(methodVarNames={"airportId","address" })
	List<Airline> findAirlineAllById(String airportId,String address) throws RemoteInvokeException;

    @RemoteMethod(methodVarNames={"airlineId"})
	List<Plane> findAllPlaneByAirlineId(String airlineId) throws RemoteInvokeException ;
    /**
	 * 新增公司
	 * 
	 * @param Airline
	 * @return
	 * @throws RemoteInvokeException
	 */
	String addAirline(Airline airline) throws RemoteInvokeException;



	/**
	 * 修改公司基本信息
	 * 
	 * @param Airline
	 * @return
	 * @throws RemoteInvokeException
	 */
	String updateAirline(Airline airline) throws RemoteInvokeException;

	/**
	 * 删除公司
	 * 
	 * @param AirlineId
	 * @return
	 * @throws RemoteInvokeException
	 */
	String deleteAirline(String airlineId) throws RemoteInvokeException;
	
}
