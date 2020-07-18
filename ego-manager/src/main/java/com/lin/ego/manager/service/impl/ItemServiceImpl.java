package com.lin.ego.manager.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ItemCatMapper;
import com.lin.ego.base.mapper.ItemDescMapper;
import com.lin.ego.base.mapper.ItemMapper;
import com.lin.ego.base.mapper.ItemParamItemMapper;
import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.pojo.ItemCat;
import com.lin.ego.base.pojo.ItemDesc;
import com.lin.ego.base.pojo.ItemParamItem;
import com.lin.ego.base.utils.FtpUtils;
import com.lin.ego.base.utils.IDUtils;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.SearchItem;
import com.lin.ego.manager.producer.ItemProducer;
import com.lin.ego.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private ItemProducer itemProducer;


    @Override
    public EUDataGridResult listAndPage(int curPage, int rows) {

        EUDataGridResult euDataGridResult = new EUDataGridResult();
        Page<Item> page = this.selectPage(new Page<Item>(curPage, rows));
        euDataGridResult.setTotal(page.getTotal());
        euDataGridResult.setRows(page.getRecords());

        return euDataGridResult;
    }

    @Override
    @Transactional
    public EgoResult save(Item item, String desc, String paramData) {
        //第一步：补全商品需要设置的属性并且插入
        //1.商品编号是手工输入的。
        long itemId = IDUtils.genItemId();
        item.setStatus(1L);
        item.setId(itemId);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        this.insert(item);

        //第二步：插入商品详情
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getUpdated());
        itemDescMapper.insert(itemDesc);

        //第三步：插入商品规格值
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(paramData);
        itemParamItem.setCreated(item.getCreated());
        itemParamItem.setUpdated(item.getUpdated());
        itemParamItemMapper.insert(itemParamItem);

        //第四步：封装Solr库的VO对象
        SearchItem searchItem = new SearchItem();
        searchItem.setId(itemId);
        searchItem.setTitle(item.getTitle());
        searchItem.setSellPoint(item.getSellPoint());
        searchItem.setImage(item.getImage());
        searchItem.setPrice(item.getPrice());
        ItemCat itemCat = itemCatMapper.selectById(item.getCid());
        searchItem.setCategoryName(itemCat.getName());

        itemProducer.send(searchItem);


        return EgoResult.ok();
    }


}
