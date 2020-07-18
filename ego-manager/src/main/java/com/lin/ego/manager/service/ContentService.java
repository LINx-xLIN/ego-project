package com.lin.ego.manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.Content;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;

public interface ContentService extends IService<Content> {

    EUDataGridResult listByCatIdAndPage(Long categoryId,int page,int rows);

    EgoResult addContent(Content content);

    EgoResult update(Content content);

    EgoResult delete(Integer[] ids);



}
