package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.OpenPage;

import java.util.List;

/**
 * Created by dam on 2014/6/29.
 */
public interface InfoServiceRemote {

    OpenPage<Information> getInformations(String openPage);
}
