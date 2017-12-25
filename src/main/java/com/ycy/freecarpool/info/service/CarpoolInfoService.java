package com.ycy.freecarpool.info.service;

import com.ycy.freecarpool.info.bean.CarpoolInfo;

import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
public interface CarpoolInfoService {

    /*保存新信息*/
    public int insertOne(CarpoolInfo carpoolInfo) ;

    /*查询拼车信息列表*/
    public List<CarpoolInfo> queryList(CarpoolInfo carpoolInfo);

    /*查询去重*/
    public int queryCount(CarpoolInfo carpoolInfo);
}
