package com.eighth.airrent.dao;


import java.util.List;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.Plane;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;

public interface AirlineDAO {
	
	Airline findAirlineById(String airlineId);
	  /**
     * 查找航空公司的飞机
     * @param openPage json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号 pageSize每页显示条数
     * @param airlineId
     * @return
     * @throws RemoteInvokeException
     */
    OpenPage<Plane> findPlaneByAirlineId(OpenPage openPage,String airlineId);
    
	List<Airline> findAirlineAllById(String AirportId,String address);
	
	String addAirline(Airline airline);


	String updateAirline(Airline airline);

	String deleteAirline(String airlineId);
	List<Plane> findPlaneByAirlineId(String airlineId);

}
