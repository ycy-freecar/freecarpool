package com.ycy.freecarpool.info.service;

import com.ycy.freecarpool.info.bean.CityInfo;

import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
public interface CityInfoService { public int insertOne(CityInfo cityInfo);

    public List<CityInfo> queryList(CityInfo cityInfo);
    public String getNameByCode(String cityCode);

}
