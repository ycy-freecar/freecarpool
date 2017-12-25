package com.ycy.freecarpool.info.controller;

import com.ycy.freecarpool.domain.ResultData;
import com.ycy.freecarpool.info.bean.CarpoolInfo;
import com.ycy.freecarpool.info.bean.CityInfo;
import com.ycy.freecarpool.info.service.CarpoolInfoService;
import com.ycy.freecarpool.info.service.CityInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
@Controller
@RequestMapping("/info")
public class CarpoolInfoController {

    private static final Logger log = LoggerFactory.getLogger(CarpoolInfoController.class);
    @Resource
    private CarpoolInfoService carpoolInfoService;
    @Resource
    private CityInfoService cityInfoService;

    @RequestMapping("/index")
    public ModelAndView publishPage(){
        ModelAndView modelAndView = new ModelAndView();
        CityInfo cityInfo = new CityInfo();
        cityInfo.setCityLevel("1");
        List<CityInfo> fromProvince = cityInfoService.queryList(cityInfo);
        modelAndView.addObject("fromProvince",fromProvince);
        modelAndView.addObject("title","路线选择");
        modelAndView.setViewName("getMydear/index");
        return modelAndView;
    }

    @RequestMapping(value="/saveInfo")
    public ModelAndView saveInfo(HttpServletRequest request){
        String fromCode = request.getParameter("fromCode");
        String fromName = request.getParameter("fromName");
        String toCode = request.getParameter("toCode");
        String toName = request.getParameter("toName");
        String infoType = request.getParameter("infoType");
        String userName = request.getParameter("userName");
        String userCount = request.getParameter("userCount");
        String mobile = request.getParameter("mobile");
        String goTime = request.getParameter("goTime");
        String remark = request.getParameter("remark");
        CarpoolInfo carpoolInfo = new CarpoolInfo();
        carpoolInfo.setInfoType(infoType);
        carpoolInfo.setFromCode(fromCode);
        carpoolInfo.setFromName(fromName);
        carpoolInfo.setUserName(userName);
        carpoolInfo.setUserCount(userCount);
        carpoolInfo.setMobile(mobile);
        carpoolInfo.setToCode(toCode);
        carpoolInfo.setToName(toName);
        carpoolInfo.setGoTime(goTime);
        carpoolInfo.setCreateTime(new Date());
        carpoolInfo.setRemark(remark);
        try {
            //防止重复提交校验
            int count = carpoolInfoService.queryCount(carpoolInfo);
            if (count==0) {
                carpoolInfoService.insertOne(carpoolInfo);
            }
        } catch (Exception e) {
            log.error("****************保存发布信息失败***************",e);
        }
        ModelAndView modelAndView = new ModelAndView("getMydear/list");
        modelAndView.addObject("fromCode",fromCode);
        modelAndView.addObject("fromName",fromName);
        modelAndView.addObject("toCode",toCode);
        modelAndView.addObject("toName",toName);
        modelAndView.addObject("infoType",infoType);
        return modelAndView;
    }

    @RequestMapping(value = "/listView")
    public ModelAndView listView(){
        ModelAndView modelAndView = new ModelAndView("getMydear/list");
        //一进来都是巨鹿到北京
        modelAndView.addObject("fromCode","130529");
        modelAndView.addObject("fromName","巨鹿");
        modelAndView.addObject("toCode","110000");
        modelAndView.addObject("toName","北京");
        return modelAndView;
    }

    @RequestMapping(value = "/infoList")
    @ResponseBody
    public ResultData infoList(HttpServletRequest request){
        String fromCode = request.getParameter("fromCode");
        String toCode = request.getParameter("toCode");
        String infoType = request.getParameter("infoType");
        CarpoolInfo carpoolInfo = new CarpoolInfo();
        carpoolInfo.setInfoType(infoType);
        carpoolInfo.setFromCode(fromCode);
        carpoolInfo.setToCode(toCode);
        //默认查询出发日期为当前时间前三天的历史，不查询所有
        Calendar currentDay = Calendar.getInstance();
        currentDay.setTime(new Date());
        currentDay.add(Calendar.DAY_OF_MONTH,-3);
        Date checkDay = currentDay.getTime();
        carpoolInfo.setCheckDate(checkDay);
        List<CarpoolInfo> infoList = carpoolInfoService.queryList(carpoolInfo);
        ResultData resultData = new ResultData();
        resultData.setFlag("1");
        resultData.putData("infoList",infoList);
        return resultData;
    }

    @RequestMapping(value="/publish")
    public ModelAndView toPublish(HttpServletRequest request){
        String fromCode = request.getParameter("fromCode");
        if (StringUtils.isEmpty(fromCode)) {
            fromCode="130529";
        }
        String fromName = request.getParameter("fromName");
        if (StringUtils.isEmpty(fromName)) {
            fromName="巨鹿";
        }
        String toCode = request.getParameter("toCode");
        if (StringUtils.isEmpty(toCode)) {
            toCode="110000";
        }
        String toName = request.getParameter("toName");
        if (StringUtils.isEmpty(toName)) {
            toName="北京";
        }
        String infoType = request.getParameter("infoType");
        ModelAndView modelAndView = new ModelAndView("getMydear/publish");
        modelAndView.addObject("fromCode",fromCode);
        modelAndView.addObject("fromName",fromName);
        modelAndView.addObject("toCode",toCode);
        modelAndView.addObject("toName",toName);
        modelAndView.addObject("infoType",infoType);
        modelAndView.addObject("title","我要发布");
        return modelAndView;
    }

    @RequestMapping(value="/error")
    public ModelAndView error(){
        return new ModelAndView("getMydear/error");
    }

    @RequestMapping(value="/introduction")
    public ModelAndView introduction(){
        return new ModelAndView("extension/explain");
    }

}
