package com.lin.ego.search.service;

import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.SearchItem;
import com.lin.ego.base.vo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;

public interface SearchService {

    List<SearchItem> gatherDate();


    List <SolrInputDocument> getDocument(List<SearchItem> items);

    EgoResult addDocuments(List<SolrInputDocument> docs);


    SearchResult doSearch(String keyword, String categoryName, String price, int page, Integer sort);

    EgoResult addDocument(SolrInputDocument document) throws IOException, SolrServerException;

}
