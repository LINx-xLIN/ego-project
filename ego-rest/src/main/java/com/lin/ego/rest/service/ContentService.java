package com.lin.ego.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.Content;
import com.lin.ego.base.vo.EgoResult;

public interface ContentService extends IService<Content> {
    EgoResult findContentByCategoryId(Long cid);
}
