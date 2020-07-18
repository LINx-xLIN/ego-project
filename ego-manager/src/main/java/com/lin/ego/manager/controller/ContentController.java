package com.lin.ego.manager.controller;

import com.lin.ego.base.pojo.Content;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    public EUDataGridResult listByCatIdAndPage(Long categoryId, int page, int rows) {
        return contentService.listByCatIdAndPage(categoryId, page, rows);
    }

    @RequestMapping("/save")
    public EgoResult addContent(Content content) {
        EgoResult result = contentService.addContent(content);
        return result;
    }

    @PostMapping("/edit")
    public EgoResult update(Content content) {
        return contentService.update(content);
    }

    @PostMapping("/delete")
    public EgoResult delete(Integer[] ids){
        return contentService.delete(ids);
    }
}
