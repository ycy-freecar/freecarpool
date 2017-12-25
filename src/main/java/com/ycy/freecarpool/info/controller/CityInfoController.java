package com.ycy.freecarpool.info.controller;

import com.ycy.freecarpool.domain.ResultData;
import com.ycy.freecarpool.info.bean.CityInfo;
import com.ycy.freecarpool.info.service.CityInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ycy on 2017/12/17.
 */
@Controller
@RequestMapping("/cityInfo")
public class CityInfoController {

    private static final Logger log = LoggerFactory.getLogger(CityInfoController.class);

    @Resource
    CityInfoService cityInfoService;
    @RequestMapping(value="/queryChildren")
    @ResponseBody
    public ResultData queryChildren(@RequestBody CityInfo cityInfo){
        ResultData result = new ResultData();
        List<CityInfo> cityInfos = cityInfoService.queryList(cityInfo);
        result.setFlag("1");
        result.setMsg("ok");
        result.putData("cityInfos",cityInfos);
        return result;
    }
}
