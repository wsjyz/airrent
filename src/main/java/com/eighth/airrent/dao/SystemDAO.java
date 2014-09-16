package com.eighth.airrent.dao;


import com.eighth.airrent.domain.APKVersion;

/**
 * Created by dam on 2014/7/30.
 */
public interface SystemDAO {

    APKVersion findLastVersion();
}
