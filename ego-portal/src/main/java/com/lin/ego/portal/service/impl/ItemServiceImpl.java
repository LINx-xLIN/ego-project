package com.lin.ego.portal.service.impl;

import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.utils.HttpClientUtils;
import com.lin.ego.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String restBasePath;

    @Override
    public Item getById(Long itemId) {


        String jsonData = HttpClientUtils.doGet(restBasePath + "/item/" + itemId);
        if(jsonData!=null){
            return JsonUtils.jsonToPojo(jsonData,Item.class);
        }

        return null;
    }
}
