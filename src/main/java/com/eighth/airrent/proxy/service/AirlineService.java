package com.eighth.airrent.proxy.service;

import java.util.List;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
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
    Airline findAirlineById(String airlineId)throws RemoteInvokeException;

    /**
     * 查找航空公司的飞机
     * @param openPage json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号 pageSize每页显示条数
     * @param airlineId
     * @return
     * @throws RemoteInvokeException
     */
    OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,String airlineId)throws RemoteInvokeException;
    
    
	List<Airline> findAirlineAllById(String AirportId) throws RemoteInvokeException;
}
