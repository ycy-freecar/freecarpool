package com.ycy.freecarpool.job;

import com.ycy.freecarpool.wx_service.QueryTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by ycy on 2017/12/14.
 */
public class AccessTokenJob {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenJob.class);

    @Resource
    QueryTokenService queryTokenService;

    //定时获取access_token
    public void execute(){
        logger.info("***************获取access_token定时任务开始*************");
        try {
            queryTokenService.queryToken();
        } catch (Exception e) {
            logger.error("*******************获取access_token定时任务失败**********************",e);
        }
    }

}
