package com.lin.ego.base.vo;

import java.util.List;

public class SearchResult {

    private Long recordCount;
    private List<SearchItem> itemList;
    private Integer totallPages;
    private Integer curPage;

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Integer getTotallPages() {
        return totallPages;
    }

    public void setTotallPages(Integer totallPages) {
        this.totallPages = totallPages;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }
}
