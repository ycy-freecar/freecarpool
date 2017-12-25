package com.ycy.freecarpool.basic.bean;

/**
 * Created by ycy on 2017/12/15.
 */
public class ViewButton extends Button {

    /*网页链接，用户点击菜单可打开链接，不超过256字节  view类型必须  */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
