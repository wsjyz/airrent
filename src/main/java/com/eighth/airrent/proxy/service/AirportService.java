package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;


/**
 * Created by dam on 2014/7/2.
 */
public interface AirportService {

    /**
     * 根据机场名称搜索 模糊匹配
     * @param airportName
     * @return
     * @throws RemoteInvokeException
     */
    OpenPage<Airport> findAirportList(String airportName)throws RemoteInvokeException;

    /**
     * 机场详情
     * @param airportId
     * @return
     * @throws RemoteInvokeException
     */
    Airport findAirportById(String airportId)throws RemoteInvokeException;

    /**
     * 查找机场下的飞机
     * @param openPage json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号 pageSize每页显示条数
     * @param airportId
     * @return
     * @throws RemoteInvokeException
     */
    OpenPage<Plane> findPlaneByAirportId(OpenPage openPage,String airportId)throws RemoteInvokeException;
}
