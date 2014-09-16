package com.eighth.airrent.service.impl;


import com.eighth.airrent.dao.SystemDAO;
import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dam on 2014/7/24.
 */
@Service("SystemService")
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemDAO systemDAO;
    @Override
    public APKVersion updateAPK(String currentVersionCode) throws RemoteInvokeException {
        APKVersion version = systemDAO.findLastVersion();
//        Integer clientVersion = 0;
//        Integer serverVersion = 0;
//        if(currentVersionCode.indexOf(".") != -1 && version != null){
//            clientVersion = Integer.parseInt(currentVersionCode.replace(".",""));
//            serverVersion = Integer.parseInt(version.getLastVersionCode().replace(".",""));
//            if(serverVersion > clientVersion){
//                return version;
//            }
//        }
        return version;
    }

}
