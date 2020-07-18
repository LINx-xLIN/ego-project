package com.lin.ego.portal.service.impl;

import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.utils.HttpClientUtils;
import com.lin.ego.base.vo.SearchResult;
import com.lin.ego.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Value("${SEARCH_ITEM_URL}")
    private String SEARCH_ITEM_URL;


    @Override
    public SearchResult query(String q, Integer page) {

        SearchResult result = null;
        Map<String, String> params = new HashMap<>();
        params.put("keyword", q);
        params.put("page", page + "");
        String jsonData = HttpClientUtils.doGet(SEARCH_BASE_URL + SEARCH_ITEM_URL, params);

        if (null != jsonData) {
             result= JsonUtils.jsonToPojo(jsonData, SearchResult.class);

        }
        return result;


    }
}
