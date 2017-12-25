package com.ycy.freecarpool.basic.bean;

/**
 * Created by ycy on 2017/12/15.
 */
public class ClickButton extends Button{
    /*菜单KEY值，用于消息接口推送，不超过128字节   click等点击类型必须 */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
