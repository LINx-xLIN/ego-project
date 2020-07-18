package com.lin.ego.manager.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.ItemParam;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemParamService extends IService<ItemParam> {

    /**
     *
     *
     * @param itemCatId
     * @return
     */
    EgoResult findItemParamByItemCatId(Long itemCatId);


    EgoResult save(Long itemCatId,String paramData);

    EUDataGridResult ListAndPage(int curPage,int pageSize);



}
