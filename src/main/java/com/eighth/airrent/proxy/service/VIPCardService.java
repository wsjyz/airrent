package com.eighth.airrent.proxy.service;

import com.eighth.airrent.domain.VIPCard;

import java.util.List;

/**
 * Created by dam on 2014/7/2.
 */
public interface VIPCardService {

    /**
     * 列出所有VIPcard信息
     * @return
     */
    List<VIPCard> findVIPCardList();

}
