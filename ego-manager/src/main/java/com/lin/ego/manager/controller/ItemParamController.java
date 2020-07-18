package com.lin.ego.manager.controller;

import com.lin.ego.base.pojo.ItemParam;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemcatid}")
     public EgoResult selectByCatId(@PathVariable("itemcatid") Long itemCatId){
            return itemParamService.findItemParamByItemCatId(itemCatId);
     }

     @RequestMapping("/save/{catId}")
     public EgoResult saveItemParam(@PathVariable("catId") Long cid ,String paramData){
         try {
             return itemParamService.save(cid,paramData);
         } catch (Exception e) {
             e.printStackTrace();
             return EgoResult.build(400,"保存规格模版失败");
         }
     }

     @GetMapping("/list")
     public EUDataGridResult listAndPage(Integer page,Integer rows){
        return itemParamService.ListAndPage(page, rows);
     }



}
