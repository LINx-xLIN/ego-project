package com.lin.ego.portal.controller;


import com.lin.ego.base.vo.SearchItem;
import com.lin.ego.base.vo.SearchResult;
import com.lin.ego.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String query(String q, @RequestParam(defaultValue = "1") Integer page, ModelMap map) {
        SearchResult result = searchService.query(q, page);

        map.put("query", q);
        map.put("totalPages", result.getTotallPages());
        map.put("itemList", result.getItemList());
        map.put("page",page);

        for (SearchItem searchItem : result.getItemList()) {
            System.out.println(searchItem);
        }


        return "search";
    }

}
