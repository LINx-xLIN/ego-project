package com.lin.ego.portal.service;

import com.lin.ego.base.vo.SearchResult;

public interface SearchService {


    SearchResult query(String q,Integer page);

}
