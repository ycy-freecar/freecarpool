package com.ycy.freecarpool.info.bean;

/**
 * Created by ycy on 2017/12/16.
 */
public class CityInfo {
    private String cityCode;
    private String cityName;
    private String cityLevel;
    private String fullName;
    private String parentCode;
    private String parentName;
    private String abbeName;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(String cityLevel) {
        this.cityLevel = cityLevel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAbbeName() {
        return abbeName;
    }

    public void setAbbeName(String abbeName) {
        this.abbeName = abbeName;
    }
}
