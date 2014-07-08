package com.eighth.airrent.dao;

import com.eighth.airrent.domain.VIPCard;

import java.util.List;

/**
 * Created by dam on 2014/7/2.
 */
public interface VIPCardDAO {

    /**
     * 列出所有VIPcard信息
     * @return
     */
    List<VIPCard> findVIPCardList();

}
