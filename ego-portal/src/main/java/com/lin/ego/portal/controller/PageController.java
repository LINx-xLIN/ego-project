package com.lin.ego.portal.controller;

import com.lin.ego.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){

        //跳转到首页的时候，将大广告位的数据放在Request作用域里面
        String adResult = contentService.getItemList();
        System.out.println(adResult);
        model.addAttribute("ads", adResult);

        return "index";
    }





}
