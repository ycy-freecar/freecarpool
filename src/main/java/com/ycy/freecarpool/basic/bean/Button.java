package com.ycy.freecarpool.basic.bean;

/**
 * Created by ycy on 2017/12/15.
 */
public class Button {
    /*按键名称，不超过16个字节 必填*/
    public String name;
    /*按键类型，必填*/
    public String type;
    /*子按钮，1-5个，非必填*/
    public Button[] sub_button;

    public String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
