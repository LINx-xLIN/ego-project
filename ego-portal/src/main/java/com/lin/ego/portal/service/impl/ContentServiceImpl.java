package com.lin.ego.portal.service.impl;

import com.lin.ego.base.pojo.Content;
import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.utils.HttpClientUtils;
import com.lin.ego.base.vo.ADItem;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.portal.service.ContentService;
import com.sun.javafx.scene.control.skin.SplitPaneSkin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${REST_BASE_URL}")
    private String restBasePath;
    @Value("${REST_INDEX_AD_URL}")
    private String indexAdURL;

    @Override
    public String getItemList() {

        String result = HttpClientUtils.doGet(restBasePath + indexAdURL);
        EgoResult egoResult = EgoResult.formatToList(result, Content.class);
        List<ADItem> itemList = new ArrayList<>();
        if(egoResult.getStatus()==200){
            List<Content> contents = (List<Content>) egoResult.getData();
            for (Content content : contents) {
                ADItem item = new ADItem();
                item.setSrc(content.getPic());
                item.setSrcB(content.getPic2());
                item.setAlt(content.getTitleDesc());
                item.setHref(content.getUrl());
                itemList.add(item);
            }
        }


        return JsonUtils.objectToJson(itemList);
    }
}
