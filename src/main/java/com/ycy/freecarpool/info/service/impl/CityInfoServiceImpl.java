package com.ycy.freecarpool.info.service.impl;

import com.ycy.freecarpool.info.bean.CityInfo;
import com.ycy.freecarpool.info.dao.CityInfoMapper;
import com.ycy.freecarpool.info.service.CityInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
@Service
public class CityInfoServiceImpl implements CityInfoService {

    private static final Logger log = LoggerFactory.getLogger(CityInfoServiceImpl.class);

    @Resource
    CityInfoMapper cityInfoMapper;

    @Override
    public int insertOne(CityInfo cityInfo) {
        int result = 0;
        try {
            result = cityInfoMapper.insertOne(cityInfo);
        } catch (Exception e) {
            log.error("**********保存城市信息失败************",e);
        }
        return result;
    }

    @Override
    public List<CityInfo> queryList(CityInfo cityInfo) {
        List<CityInfo> cityInfos = new ArrayList<CityInfo>();
        try {
            cityInfos = cityInfoMapper.queryList(cityInfo);
        } catch (Exception e) {
            log.error("**********查询城市列表失败**********",e);
        }
        return cityInfos;
    }

    @Override
    public String getNameByCode(String cityCode){
        String cityName = "";
        try {
            cityName = cityInfoMapper.getNameByCode(cityCode);
        } catch (Exception e) {
            log.error("**********查询城市名称失败**********",e);
        }
        return cityName;
    }
}
