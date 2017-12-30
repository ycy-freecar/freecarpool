package com.ycy.freecarpool.info.bean;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ycy on 2017/12/16.
 * 拼车信息javabean
 */
public class CarpoolInfo {
    private int id;
    private String fromCode;
    private String fromName;
    private String toCode;
    private String toName;
    private String infoType;
    private String mobile;
    private String goTime;
    private String userName;
    private String userCount;
    private Date createTime;
    private String createTimeShow;
    private Date checkDate;
    private String remark;
    private String tripOver;
    private String goTimeShow;
    private String validFlag;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCreateTimeShow() {
        if (createTime == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Long currentTime = System.currentTimeMillis();
        Long ct = createTime.getTime();
        Long min = (currentTime-ct)/1000/60;
        Long h = (currentTime-ct)/1000/60/60;
        Long d = (currentTime-ct)/1000/60/60/24;
        if (d>0) {
            if (d<5) {
                return d+"天前";
            } else {
                return df.format(createTime);
            }
        }
        if (h>0) {
            return h+"小时前";
        }
        if (min>1) {
            return min+"分钟前";
        } else {
            return "刚刚";
        }
    }

    public void setCreateTimeShow(String createTimeShow) {
        this.createTimeShow = createTimeShow;
    }

    public String getTripOver() throws Exception{
        //行程结束标识符
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date tripDate = df.parse(goTime);
        Date currentDate = df.parse(df.format(new Date()));
        if (tripDate.before(currentDate)) {
            return "1";
        } else {
            return "0";
        }
    }

    public void setTripOver(String tripOver) {
        this.tripOver = tripOver;
    }

    public String getGoTimeShow() throws Exception{
        return goTimeShow;
    }

    public void setGoTimeShow(String goTimeShow) {
        this.goTimeShow = goTimeShow;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }
}
