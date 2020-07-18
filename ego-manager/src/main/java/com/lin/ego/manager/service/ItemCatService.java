package com.lin.ego.manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.ItemCat;
import com.lin.ego.base.vo.EUTreeNode;

import java.util.List;

public interface ItemCatService extends IService<ItemCat> {
    List<EUTreeNode> getByParentId(Long parentId);
}
