package com.ycy.freecarpool.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonConverUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonConverUtil.class);

    public static String Bean2Json(Object bean) {
        String json = "";
        try {
            json = JSON.toJSONString(bean, SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            log.error("数据解析失败 object "+bean.toString()+"  stack "+e.getStackTrace());
        }
        return json;
    }

    public static <T> T Json2Bean(String json, Class<T> clazz) {
        T obj = null;
        try {
            obj = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            log.error("数据解析失败 object "+json.toString()+"  stack "+e.getStackTrace());
        }
        return obj;
    }

    public static Map<String,Object> Json2Map(String json){
        Map<String,Object>jMap = new HashMap<String,Object>();
        JSONObject jObj = JSONObject.parseObject(json);
        Set<String> keys = jObj.keySet();
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            jMap.put(key,jObj.get(key));
        }
        return jMap;
    }

}
