package com.lin.ego.rest.controller;


import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/category/{cid}")
    public EgoResult findByCategory(@PathVariable Long cid) {
        return contentService.findContentByCategoryId(cid);
    }

}
