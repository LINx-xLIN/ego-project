package com.lin.ego.manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.ContentCategory;
import com.lin.ego.base.vo.EUTreeNode;
import com.lin.ego.base.vo.EgoResult;

import java.util.List;

public interface ContentCategoryService extends IService<ContentCategory> {
    List<EUTreeNode> selectByParentId(Long parentId);

    EgoResult save(Long parentId,String name);

    EgoResult updateNode(String name,Long id);

    EgoResult deleteNode(Long id, Long parentId);
}
