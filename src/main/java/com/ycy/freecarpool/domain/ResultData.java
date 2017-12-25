package com.ycy.freecarpool.domain;

import java.util.HashMap;

/**
 * Created by ycy on 2017/12/17.
 */
public class ResultData {
    private String flag;
    private String msg;
    private HashMap data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public void putData(String key,Object val){
        if (this.data == null) {
            this.data = new HashMap();
        }
        this.data.put(key,val);
    }

    public Object getData(String key){
        if (this.data == null) {
            return null;
        } else {
            return this.data.get(key);
        }
    }
}
