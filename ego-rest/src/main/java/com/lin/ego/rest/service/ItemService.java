package com.lin.ego.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.Item;

public interface ItemService extends IService<Item> {

    Item getById(Long itemId);


}
