package com.lin.ego.manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;

public interface ItemService extends IService<Item> {
    /**
     * 分页查询，返回结果
     * @param curPage 当前索引
     * @param rows 每页记录数
     * @return 返回EasyUI的Vo类型
     */
    EUDataGridResult listAndPage(int curPage, int rows);

    EgoResult save(Item item, String desc, String paramData);
}
