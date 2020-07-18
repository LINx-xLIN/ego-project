package com.lin.ego.rest.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ItemMapper;
import com.lin.ego.base.pojo.Item;
import com.lin.ego.rest.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Override
    public Item getById(Long itemId) {
        return this.selectById(itemId);
    }
}
