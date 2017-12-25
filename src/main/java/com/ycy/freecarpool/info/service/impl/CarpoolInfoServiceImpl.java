package com.ycy.freecarpool.info.service.impl;

import com.ycy.freecarpool.info.bean.CarpoolInfo;
import com.ycy.freecarpool.info.dao.CarpoolInfoMapper;
import com.ycy.freecarpool.info.dao.CityInfoMapper;
import com.ycy.freecarpool.info.service.CarpoolInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
@Service
@Transactional
public class CarpoolInfoServiceImpl implements CarpoolInfoService{

    private static final Logger logger = LoggerFactory.getLogger(CarpoolInfoServiceImpl.class);

    @Resource
    private CarpoolInfoMapper carpoolInfoMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insertOne(CarpoolInfo carpoolInfo) {
        return carpoolInfoMapper.insertOne(carpoolInfo);
    }

    @Override
    public List<CarpoolInfo> queryList(CarpoolInfo carpoolInfo){
        List<CarpoolInfo> carpoolInfos = new ArrayList<CarpoolInfo>();
        try {
            carpoolInfos = carpoolInfoMapper.queryList(carpoolInfo);
        } catch (Exception e) {
            logger.error("**********查询信息列表失败**********",e);
        }
        return carpoolInfos;
    }


    public int queryCount(CarpoolInfo carpoolInfo){
        int count = 0;
        try {
            count = carpoolInfoMapper.queryCount(carpoolInfo);
        } catch (Exception e) {
            throw e;
        }
        return count;
    }
}
