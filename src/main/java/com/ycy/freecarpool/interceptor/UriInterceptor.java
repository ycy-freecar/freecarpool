package com.ycy.freecarpool.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ycy on 2017/12/21.
 */
public class UriInterceptor implements HandlerInterceptor{
    private static final Logger log = LoggerFactory.getLogger(UriInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String fromUserName =(String) session.getAttribute("fromUserName");
        log.info("***************当前用户及session****************FromUserName："+fromUserName+",session="+session.getId());
        if (StringUtils.isNotEmpty(fromUserName)) {
            return true;
        }
        String url = request.getRequestURI();
        log.info("*******URL*******"+url);
        if (url.contains("/wechat/") || url.contains("/public/")) {
            log.info("*******放行*******");
            return true;
        } else {
            log.info("*******拦截*******");
            request.getRequestDispatcher("/wechat/error").forward(request, response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
