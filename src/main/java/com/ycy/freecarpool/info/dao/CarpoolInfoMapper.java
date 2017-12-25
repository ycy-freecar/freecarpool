package com.ycy.freecarpool.info.dao;

import com.ycy.freecarpool.info.bean.CarpoolInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
@Component("carpoolInfoMapper")
public interface CarpoolInfoMapper {
    /*保存新信息*/
    public int insertOne(CarpoolInfo carpoolInfo);

    /*查询拼车信息列表*/
    public List<CarpoolInfo> queryList(CarpoolInfo carpoolInfo);

    public int queryCount(CarpoolInfo carpoolInfo);

}
