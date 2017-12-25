package com.ycy.freecarpool.info.dao;

import com.ycy.freecarpool.info.bean.CityInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
@Component("cityInfoMapper")
public interface CityInfoMapper {

    public int insertOne(CityInfo cityInfo);

    public List<CityInfo> queryList(CityInfo cityInfo);

    public String getNameByCode(String cityCode);
}
