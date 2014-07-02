package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.Information;
import com.eighth.airrent.domain.OpenPage;

import java.util.List;

/**
 * Created by dam on 2014/6/29.
 */
public interface InfoService {

    /**
     * 获取新闻
     * @param openPage json格式 { "pageNo": 0, "pageSize": 10 }pageNo页号 pageSize每页显示条数
     * @return
     */
    OpenPage<Information> getInformations(OpenPage openPage);
}
