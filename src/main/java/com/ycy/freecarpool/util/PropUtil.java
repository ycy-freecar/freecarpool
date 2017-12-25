package com.ycy.freecarpool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by ycy on 2017/12/14.
 */
public class PropUtil {
    private static final Logger log = LoggerFactory.getLogger(PropUtil.class);

    public static void WriteProp(String filepath,String key,String value){
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(filepath);
            properties.load(in);
            properties.setProperty(key,value);
            OutputStream out = new FileOutputStream(filepath);
            properties.store(out,"Update "+key);
        } catch (Exception e) {
            log.error("**************更新prop失败*****************",e);
        }
    }

    public static String readProp(String filepath,String key){
        Properties properties = new Properties();
        String value = "";
        try {
            FileInputStream in = new FileInputStream(filepath);
            properties.load(in);
            value =  properties.getProperty(key);
        } catch (Exception e) {
            log.error("**************获取prop值失败*****************",e);
        }
        return value;
    }

    public static void main (String args[]){
        //URL path = PropUtil.class.getResource("");
        String path = PropUtil.class.getClassLoader().getResource("token.properties").getPath();
        //System.out.println(path);
        WriteProp(path,"ACCESS_TOKEN","yuanchaoyong");
        String token = readProp(path,"ACCESS_TOKEN");
        System.out.println(token);
    }
}
