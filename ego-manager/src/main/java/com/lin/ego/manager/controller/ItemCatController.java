package com.lin.ego.manager.controller;

import com.lin.ego.base.vo.EUTreeNode;
import com.lin.ego.manager.service.ItemCatService;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<EUTreeNode> initTreeByParent(@RequestParam(defaultValue = "0") Long id){

        return itemCatService.getByParentId(id);

    }

}
