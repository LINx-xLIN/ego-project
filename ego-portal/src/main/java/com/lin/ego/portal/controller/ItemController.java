package com.lin.ego.portal.controller;


import com.lin.ego.base.pojo.Item;
import com.lin.ego.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/{itemId}")
    public String getItemById(@PathVariable("itemId") Long itemId  , Model model) {
        Item item = itemService.getById(itemId);
        if (item!=null) {
            model.addAttribute("item", item);
            return "item";
        }else {
            model.addAttribute("message", "外星人把服务器抢走了，地球卫士正在修复!");
            return "error/exception";
        }

    }

}
