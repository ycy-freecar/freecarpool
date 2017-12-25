package com.ycy.freecarpool.basic.controller;

import com.ycy.freecarpool.wx_service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ycy on 2017/12/16.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;

    @RequestMapping("/createMenu")
    public void createMenu(HttpServletResponse response){
        String code = menuService.createMenu();
        try {
            response.getWriter().println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
