package com.lin.ego.manager.controller;


import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/{id}")
    public Item getById(@PathVariable("id") long id) {
        Item item = itemService.selectById(id);

        return item;
    }

    @GetMapping(value = "/list")
    public EUDataGridResult list(int page, int rows) {

        try {
            return itemService.listAndPage(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //异常处理情况，和前端开发人员核对
        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(0);
        result.setRows(null);

        return result;
    }
    @PostMapping(value="/save")
    public EgoResult save(Item item,String desc,String itemParams){
        EgoResult result = null;

        try {
            return  itemService.save(item,desc,itemParams);
        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.build(400, "保存失败，请稍后再试");
        }
    }


}
