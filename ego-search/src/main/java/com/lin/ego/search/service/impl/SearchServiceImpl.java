package com.lin.ego.search.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.lin.ego.base.mapper.ItemMapper;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.SearchItem;
import com.lin.ego.base.vo.SearchResult;
import com.lin.ego.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private HttpSolrServer httpSolrServer;
    @Autowired
    private ItemMapper itemMapper;

    @Value("${solr.pageSize}")
    private int pageSize;


    @Override
    public List<SearchItem> gatherDate() {




        return itemMapper.gatherDate();
    }

    @Override
    public List<SolrInputDocument> getDocument(List<SearchItem> items) {

        List<SolrInputDocument> docs = new ArrayList<>();

        SolrInputDocument doc = null;

        for(SearchItem item:items){
             doc = new SolrInputDocument();

             doc.addField("id",item.getId());
             doc.addField("item_title",item.getTitle());
             doc.addField("item_category_name",item.getCategoryName());
             doc.addField("item_price",item.getPrice());
             doc.addField("item_sell_point",item.getSellPoint());
             doc.addField("item_image",item.getImage());

            docs.add(doc);


        }


        return docs;
    }

    @Override
    public EgoResult addDocuments(List<SolrInputDocument> docs) {

        try {
            httpSolrServer.add(docs);
            httpSolrServer.commit();
            return EgoResult.ok();


        } catch (Exception e) {
            e.printStackTrace();

            return EgoResult.build(400, "连接solr服务器异常");
        }


    }

    @Override
    public SearchResult doSearch(String keyword, String categoryName, String price, int page, Integer sort) {
            SearchResult result = null;

        try {
            result = new SearchResult();

            SolrQuery solrQuery = this.getSolrQuery(keyword, categoryName, price, page, sort);
            QueryResponse query = httpSolrServer.query(solrQuery);
            SolrDocumentList solrDocumentList = query.getResults();

            List<SearchItem> searchItems = new ArrayList<>();

            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();

            for (SolrDocument document : solrDocumentList) {
                SearchItem searchItem = new SearchItem();
                searchItem.setId(Long.valueOf((String) document.get("id")));

                Map<String, List<String>> map = highlighting.get(document.get("id"));

                List<String> feildHighlighting = map.get("item_title");
                if (feildHighlighting.size() > 0) {
                    searchItem.setTitle((feildHighlighting.get(0)));

                } else {
                    searchItem.setTitle(document.get("item_title").toString());
                }

                searchItem.setCategoryName((String) document.get("item_category_name"));
                searchItem.setPrice((Long) document.get("item_price"));
                searchItem.setImage((String) document.get("item_image"));

                searchItem.setSellPoint((String) document.get("item_sell_point"));

                searchItems.add(searchItem);

            }

            result.setItemList(searchItems);
            result.setRecordCount(solrDocumentList.getNumFound());
            result.setCurPage(page);

            int totalPage = (int) Math.ceil(result.getRecordCount() / this.pageSize);
            result.setTotallPages(totalPage);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public EgoResult addDocument(SolrInputDocument document) throws IOException, SolrServerException {
        httpSolrServer.add(document);
        httpSolrServer.commit();



        return EgoResult.ok();
    }

    private SolrQuery getSolrQuery(String keyword,String categoryName,String price,int page,Integer sort){
        SolrQuery solrQuery = new SolrQuery();

        if(!StringUtils.isEmpty(keyword)){
            solrQuery.set("q",keyword);
        }else {
            solrQuery.set("q","*");
        }

        if (!StringUtils.isEmpty(categoryName)) {
            solrQuery.add("fq","item_category_name:"+categoryName);
        }

        if(!StringUtils.isEmpty(price)){
            String[] split = price.split("-");
            solrQuery.add("fq","item_price:["+split[0]+" TO "+split[1]+"]");
        }

        if(sort==null||sort==0){
            solrQuery.setSort("item_price",SolrQuery.ORDER.asc);
        }else{
            solrQuery.setSort("item_price",SolrQuery.ORDER.desc);
        }

        int start=(page-1)*pageSize;
        solrQuery.setStart(start);
        solrQuery.setRows(pageSize);

        solrQuery.set("df", "item_title");

        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<font style='color:red'>");
        solrQuery.setHighlightSimplePost("</font>");
        solrQuery.addHighlightField("item_title");


        return solrQuery;

    }


}
