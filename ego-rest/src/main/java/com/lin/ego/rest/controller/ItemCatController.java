package com.lin.ego.rest.controller;

import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.pojo.Menu;
import com.lin.ego.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/item/all",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getMenu(String callback){
        Menu menu = itemCatService.initMenu();
        String s = JsonUtils.objectToJson(menu);
        String jsMenu = callback +"("+ s+")";
        return jsMenu;
    }
}
