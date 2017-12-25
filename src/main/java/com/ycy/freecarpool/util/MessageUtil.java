package com.ycy.freecarpool.util;

import com.thoughtworks.xstream.XStream;
import com.ycy.freecarpool.domain.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by ycy on 2017/12/20.
 */
public class MessageUtil {
    //消息对应的类型
    public static final String MESSAGE_TEXT="text";//文本消息
    public static final String MESSGAE_IMAGE="image";//图片消息
    public static final String MESSGAE_VOICE="voice";//语音消息
    public static final String MESSGAE_VIDEO="video";//视频消息
    public static final String MESSGAE_SHORTVIDEO="shortvideo";//小视频消息
    public static final String MESSGAE_LOCATION="location";//地理位置消息
    public static final String MESSGAE_LINK="link";//链接消息
    public static final String MESSGAE_NEWS="news";//新闻消息
    public static final String MESSGAE_MUSIC="music";//音乐消息
    public static final String MESSGAE_EVENT="event";//关注/取消关注事件

    //事件类型
    public static final String MESSGAE_EVENT_SUBSCRIBE="subscribe";//扫描带参数二维码事件
    public static final String MESSGAE_EVENT_SCAN="SCAN";// 用户已关注时的事件推送
    public static final String MESSGAE_EVENT_LOCATION="LOCATION";//上报地理位置事件
    public static final String MESSGAE_EVENT_LOCATION_SELECT="location_select";//上报地理位置事件
    public static final String MESSGAE_EVENT_CLICK="CLICK";//自定义菜单
    public static final String MESSGAE_EVENT_VIEW="VIEW";//点击菜单跳转链接时的事件推送

    /**
     * xml 变成 map
     * @param request
     * @return map 集合
     * @throws IOException
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xml2Map(HttpServletRequest request) throws IOException, DocumentException{
        Map<String, String> map=new HashMap<String, String>();
        SAXReader reader=new SAXReader();
        ServletInputStream inputStream = request.getInputStream();
        Document document = reader.read(inputStream);
        Element element = document.getRootElement();
        List<Element> list = element.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;

    }

    /**
     * 将test消息 转成xml
     * @param  test消息实体
     * @return xml 格式的text消息
     */
    public static String textMessage2xml(TextMessage msg){
        XStream xstream= new XStream();
        xstream.alias("xml", msg.getClass());
        return xstream.toXML(msg);
    }



    /**
     * 初始化Test消息
     * @param toUserName 消息接受者
     * @param fromUserName 消息发送者
     * @param content 回复的内容
     * @return xml 格式的字符串
     */
    public static String initText(String toUserName,String fromUserName,String content){
        TextMessage msg=new TextMessage();
        msg.setContent(content);
        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        msg.setMsgType(MESSAGE_TEXT);
        msg.setCreateTime(((Long)new Date().getTime()).toString());
        return textMessage2xml(msg);
    }

    /**
     * 将图文消息 转成xml
     * @param NewsMessage 图文实体
     * @return xml格式的字符串
     */
    public static String newsMessage2xml(NewsMessage msg){
        XStream xstream= new XStream();
        xstream.alias("xml", msg.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(msg);
    }

    /**
     * 初始化图片消息
     * @param toUserName 消息接受者
     * @param fromUserName 消息发送者
     * @return xml格式的图文消息
     */
    public static String initNewsMessage(String toUserName,String fromUserName,String url){
        News news=new News();
        news.setPicUrl(url);
        List<News> list=new ArrayList<News>();
        list.add(news);
        NewsMessage msg=new NewsMessage();
        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        msg.setMsgType(MESSGAE_NEWS);
        msg.setCreateTime(((Long)new Date().getTime()).toString());
        msg.setArticleCount(1);
        msg.setArticles(list);
        return newsMessage2xml(msg);
    }

    /**
     * 初始化图文消息
     * @param toUserName 消息接受者
     * @param fromUserName 消息发送者
     * @return xml格式的图文消息
     */
    public static String initNewsMessage(String toUserName,String fromUserName){
        News news=new News();
        news.setDescription("微信公众平台是运营者通过公众号为微信用户提供资讯和服务的平台，而公众平台开发接口则是提供服务" +
                "的基础，开发者在公众平台网站中创建公众号、获取接口权限后，可以通过阅读本接口文档来帮助开发。 " +
                "为了识别用户，每个用户针对每个公众号会产生一个安全的OpenID，如果需要在多公众号、移动应用之间" +
                "做用户共通，则需前往微信开放平台，将这些公众号和应用绑定到一个开放平台账号下，绑定后，一个用" +
                "户虽然对多个公众号和应用有多个不同的OpenID，但他对所有这些同一开放平台账号下的公众号和应用，" +
                "只有一个UnionID，可以在用户管理-获取用户基本信息（UnionID机制）文档了解详情。");
        news.setTitle("logo");
        news.setPicUrl("http://example.tunnel.mobi/ganshan-front/images/1.jpg");
        news.setUrl("http://example.tunnel.mobi/ganshan-front");
        List<News> list=new ArrayList<News>();
        list.add(news);
        NewsMessage msg=new NewsMessage();
        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        msg.setMsgType(MESSGAE_NEWS);
        msg.setCreateTime(((Long)new Date().getTime()).toString());
        msg.setArticleCount(1);
        msg.setArticles(list);
        return newsMessage2xml(msg);
    }

    /**
     * 图片消息转xml
     * @param ImageMessage 图片实体
     * @return xml格式的图片消息
     */
    public static String imageMessage2xml(ImageMessage msg){
        XStream xstream= new XStream();
        xstream.alias("xml", msg.getClass());
        xstream.alias("item", new Image().getClass());
        return xstream.toXML(msg);
    }

    /**
     * 初始化图片消息
     * @param toUserName 消息接受者
     * @param fromUserName 消息发送者
     * @return xml格式的图片消息
     */
    public static String initImageMessage(String toUserName,String fromUserName){
        Image image=new Image();
        image.setMediaId("3M0oF7mWzY2eOzzO8qMVrt1gTXyHis2RRRdM7AWb8WPJzyC94D_K4F2nuwEHSHta");
        ImageMessage msg=new ImageMessage();
        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        msg.setMsgType(MESSGAE_IMAGE);
        msg.setCreateTime(((Long)new Date().getTime()).toString());
        msg.setImage(image);
        return imageMessage2xml(msg);
    }

    /**
     * 音乐消息转xml
     * @param ImageMessage 图片实体
     * @return xml格式的音乐消息
     */
    public static String musicMessage2xml(MusicMessage msg){
        XStream xstream= new XStream();
        xstream.alias("xml", msg.getClass());
        xstream.alias("Music", new Music().getClass());
        return xstream.toXML(msg);
    }

    /**
     * 初始化图片消息
     * @param toUserName 消息接受者
     * @param fromUserName 消息发送者
     * @return xml格式的图片消息
     */
    public static String initMusicMessage(String toUserName,String fromUserName){
        Music music=new Music();
        music.setDescription("描述：10年再见");
        music.setHQMusicUrl("http://example.tunnel.mobi/ganshan-front/images/1.mp3");
        music.setMusicUrl("http://example.tunnel.mobi/ganshan-front/images/1.mp3");
        music.setThumbMediaId("v8Lx96arL5ODxkwwJ8FbwtsTeliKj0nCRIAayopYjsklJpfYNciDF0v8h_giPeji");
        music.setTitle("再见");
        MusicMessage msg=new MusicMessage();
        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        msg.setMsgType(MESSGAE_MUSIC);
        msg.setCreateTime(((Long)new Date().getTime()).toString());
        msg.setMusic(music);
        return musicMessage2xml(msg);
    }
    /**
     * 主菜单
     * @return 字符串
     */
    public static String menuText(){
        StringBuffer sb=new StringBuffer();
        sb.append("欢迎你的关注：请按照菜单提示经销操作：\n\n");
        sb.append("1:爱心供销介绍\n");
        sb.append("2:订单流程\n");
        sb.append("回复?调出此菜单\n");
        return sb.toString();
    }
    /**
     * 爱心供销介绍
     * @return
     */
    public static String jieshao(){
        StringBuffer sb=new StringBuffer();
        sb.append("一家专门做专卖的网站，上面有你爱吃的各种食材\n\n");
        return sb.toString();
    }
    /**
     * 订单流程
     * @return
     */
    public static String orderText(){
        StringBuffer sb=new StringBuffer();
        sb.append("1:下单\n");
        sb.append("2:支付\n");
        sb.append("3:代发货\n");
        sb.append("4:收货\n");
        sb.append("5:交易成功\n");
        return sb.toString();
    }


}
