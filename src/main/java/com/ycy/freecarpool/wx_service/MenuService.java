package com.ycy.freecarpool.wx_service;

import com.alibaba.fastjson.JSONObject;
import com.ycy.freecarpool.basic.bean.Button;
import com.ycy.freecarpool.basic.bean.Menu;
import com.ycy.freecarpool.basic.bean.ViewButton;
import com.ycy.freecarpool.util.HttpClientUtil;
import com.ycy.freecarpool.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ycy on 2017/12/16.
 */
public class MenuService {
    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    private String host;
    private String wxHost;
    //创建
    private static final String CREATE_MENU_URL="/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 主页菜单
     * @return
     */
    public Menu initMainPageMenu(){
        Menu mainMenu = new Menu();
        ViewButton mainBtn1 = new ViewButton();
        mainBtn1.setName("我要拼车");
        mainBtn1.setKey("carpoolBtn1");
        mainBtn1.setUrl(host+"/info/listView");
        mainBtn1.setType("view");

        ViewButton mainBtn2 = new ViewButton();
        mainBtn2.setName("我要发布");
        mainBtn2.setKey("carpoolBtn2");
        mainBtn2.setUrl(host+"/info/publish");
        mainBtn2.setType("view");

        List<Button> buttons = new ArrayList<Button>();
        buttons.add(mainBtn1);
        buttons.add(mainBtn2);
        mainMenu.setButton(buttons);

        return mainMenu;
    }

    public String createMenu(){
        String url = wxHost+CREATE_MENU_URL;
        String path = this.getClass().getClassLoader().getResource("token.properties").getPath();
        String accessToken = PropUtil.readProp(path,"ACCESS_TOKEN");
        url = url.replace("ACCESS_TOKEN",accessToken);
        Menu mainMenu = initMainPageMenu();
        log.info("*********createMenu***********[url:"+url+"]");
        String result = "";
        try {
            result = HttpClientUtil.Post(url, JSONObject.toJSONString(mainMenu), "UTF-8");
        } catch (Exception e) {
            log.error("*********创建菜单失败***********",e);
        }
        return result;
    }


    public void setHost(String host) {
        this.host = host;
    }

    public void setWxHost(String wxHost) {
        this.wxHost = wxHost;
    }
}
