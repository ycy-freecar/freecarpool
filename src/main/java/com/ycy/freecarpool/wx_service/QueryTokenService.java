package com.ycy.freecarpool.wx_service;

import com.ycy.freecarpool.job.AccessTokenJob;
import com.ycy.freecarpool.util.HttpClientUtil;
import com.ycy.freecarpool.util.JsonConverUtil;
import com.ycy.freecarpool.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ycy on 2017/12/15.
 */
public class QueryTokenService {

    private String appID;
    private String appSecret;
    private String weiXinHost;

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenJob.class);


    //定时获取access_token
    public String queryToken(){
        logger.info("***************获取access_token开始*************");
        String accessToken = "";
        String url = weiXinHost+"/cgi-bin/token";
        HashMap<String,String> requestMap = new HashMap<String,String>();
        requestMap.put("appid",appID);
        requestMap.put("secret",appSecret);
        requestMap.put("grant_type","client_credential");
        try {
            String result = HttpClientUtil.Get(url, requestMap);
            logger.info("************tokenMessage*****************:"+result);
            Map<String,Object> resultMap = JsonConverUtil.Json2Map(result);
            accessToken = (String)resultMap.get("access_token");
            String path = this.getClass().getClassLoader().getResource("token.properties").getPath();
            //将token写到配置文件中
            PropUtil.WriteProp(path,"ACCESS_TOKEN",accessToken);
            logger.info("*******************解析accessToken成功**********************");
        } catch (Exception e) {
            logger.error("*******************获取accessToken失败**********************",e);
        }
        return accessToken;
    }



    public void setAppID(String appID) {
        this.appID = appID;
    }


    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setWeiXinHost(String weiXinHost) {
        this.weiXinHost = weiXinHost;
    }
}
