package com.lin.ego.rest.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.pojo.Menu;
import com.lin.ego.base.pojo.MenuNode;
import com.lin.ego.rest.service.ItemCatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml","classpath:spring-data.xml"})
public class ItemCatServiceImplTest {
    @Autowired
    private ItemCatService itemCatService;

    @Test
    public void initMenu() {
        Menu menu = itemCatService.initMenu();
        List<?> data = menu.getData();
        String s = JsonUtils.objectToJson(data);

        System.out.println(s);


    }
}