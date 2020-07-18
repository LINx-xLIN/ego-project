package com.lin.ego.rest.controller;

import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}")
    public Item findItemById(@PathVariable Long itemId){
        return itemService.getById(itemId);
    }


}
