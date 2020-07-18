package com.lin.ego.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ItemParamMapper;
import com.lin.ego.base.pojo.ItemParam;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {
    @Autowired
    private ItemParamMapper itemParamMapper;

    @Override
    public EgoResult findItemParamByItemCatId(Long itemCatId) {

        EgoResult egoResult = new EgoResult();


        Wrapper<ItemParam> wrapper = new EntityWrapper<ItemParam>();
        wrapper.eq("item_cat_id", itemCatId);
        List<ItemParam> itemParams = this.selectList(wrapper);
        if(itemParams!=null&&itemParams.size()>0){
            return EgoResult.ok(itemParams.get(0));
        }else{
            return EgoResult.build(400, "查询不到规格项模板数据");
        }


    }

    @Override
    @Transactional
    public EgoResult save(Long itemCatId, String paramData) {

        ItemParam itemParam = new ItemParam();
        itemParam.setCreated(new Date());
        itemParam.setItemCatId(itemCatId);
        itemParam.setParamData(paramData);
        itemParam.setUpdated(itemParam.getCreated());
        this.insert(itemParam);


        return EgoResult.ok();
    }

    @Override
    public EUDataGridResult ListAndPage(int curPage, int pageSize) {

        EUDataGridResult result = new EUDataGridResult();
        int start = (curPage - 1) * pageSize;
        List<Map<String, Object>> maps = itemParamMapper.ListAndPage(start, pageSize);
        Integer count = itemParamMapper.selectCount(null);
        result.setRows(maps);
        result.setTotal(count);


        return result;
    }


}
