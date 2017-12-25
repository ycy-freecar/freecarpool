package com.ycy.freecarpool.auth.controller;

import com.ycy.freecarpool.auth.bean.WeiXinMessage;
import com.ycy.freecarpool.domain.TextMessage;
import com.ycy.freecarpool.util.MessageUtil;
import com.ycy.freecarpool.util.SignUtil;
import com.ycy.freecarpool.wx_service.QueryTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by ycy on 2017/12/11.
 */
@Controller
@RequestMapping(value="/wechat")
public class SignController {
    Logger logger = LoggerFactory.getLogger(SignController.class);

    @Resource
    QueryTokenService queryTokenService;

    @RequestMapping(value="/replay",method = {RequestMethod.POST,RequestMethod.GET})
    public void weiXMsg(HttpServletRequest request, HttpServletResponse response){
        try {
            String method = request.getMethod().toLowerCase();
            if ("get".equals(method)) {
                logger.info("***********进入身份认证方法****************");
                //加密签名
                String signature = request.getParameter("signature");
                //时间戳
                String timestamp = request.getParameter("timestamp");
                // 随机数
                String nonce = request.getParameter("nonce");
                // 随机字符串
                String echostr = request.getParameter("echostr");

                logger.info("**************验证请求信息*****************");
                logger.info("signature="+signature+",timestamp="+timestamp+",nonce="+nonce+",echostr"+echostr);
                logger.info("**************验证请求信息*****************");
                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                    response.setContentType("text/plain;charset=utf-8");
                    response.getWriter().write(echostr);
                    response.getWriter().flush();
                    logger.info("*********验签成功*********");
                } else {
                    logger.info("*********验签失败*********");
                }
            } else {
                logger.info("******WeiXinMessage*****");
                Map<String, String> message = MessageUtil.xml2Map(request);
                String msgType = message.get("MsgType");
                String event = message.get("Event");
                String fromUserName = message.get("FromUserName");
                String toUserName = message.get("ToUserName");
                String eventKey = message.get("EventKey");
                HttpSession session = request.getSession();
                logger.info("***************当前用户及session****************FromUserName："+fromUserName+",session="+session.getId());
                session.setAttribute("fromUserName",fromUserName);
                logger.info("******WeiXinMessage*****[msgType="+msgType+",event="+event+",fromUserName="+fromUserName+"," +
                        "toUserName="+toUserName+",eventKey="+eventKey+"]");
                if ("text".equals(msgType)) {
                    TextMessage textMessage = new TextMessage();
                    textMessage.setFromUserName(toUserName);
                    textMessage.setToUserName(fromUserName);
                    textMessage.setCreateTime(new Date().getTime() + "");
                    textMessage.setContent("您好，您的留言已保存，我们后续会处理，感谢您的支持！");
                    textMessage.setMsgType("text");
                    String xml = MessageUtil.textMessage2xml(textMessage);
                    response.getWriter().write(xml);
                } else if ("event".equals(msgType)) {
                    if ("subscribe".equals(event)) {
                        TextMessage textMessage = new TextMessage();
                        textMessage.setFromUserName(toUserName);
                        textMessage.setToUserName(fromUserName);
                        textMessage.setCreateTime(new Date().getTime() + "");
                        textMessage.setContent("绿色出行，自由拼车，感谢您关注爱自由拼车！" +
                                "您可以在<a href='http://51xiaopao.cc/info/introduction'>《爱自由拼车-平台简介》</a>中查看本平台的介绍和操作说明，" +
                                "后续在使用中有任何建议和意见，请您留言给我们。");
                        textMessage.setMsgType("text");
                        String xml = MessageUtil.textMessage2xml(textMessage);
                        response.getWriter().write(xml);
                    } else if ("unsubscribe".equals(event)) {
                        //TODO remove openID
                        response.getWriter().write("");
                    } else if ("CLICK".equals(event)) {
                        logger.info("***********用户点击了菜单*************");
                        response.getWriter().write("");
                    }
                }
                response.getWriter().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value="/getToken")
    public void getAccessToken(){
        queryTokenService.queryToken();
    }

    @RequestMapping(value="/error")
    public ModelAndView parseError(){
        return new ModelAndView("getMydear/error");
    }
}
