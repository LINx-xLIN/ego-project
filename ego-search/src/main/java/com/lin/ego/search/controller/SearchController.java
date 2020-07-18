package com.lin.ego.search.controller;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.SearchResult;
import com.lin.ego.search.service.SearchService;
import com.sun.tracing.dtrace.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;


    @GetMapping("/import/all")
    public EgoResult createIndex(){
        try {
            return searchService.addDocuments(searchService.getDocument(searchService.gatherDate()));
        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.build(400, "Solr创建索引库失败");
        }
    }

    @GetMapping("/doSearch")
    public SearchResult doSearch(String keyword, String categoryName, String price , @RequestParam(defaultValue = "1")Integer page, Integer sort){

        return searchService.doSearch(keyword, categoryName, price, page, sort);
    }





}
