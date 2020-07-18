package com.lin.ego.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ItemCatMapper;
import com.lin.ego.base.pojo.ItemCat;
import com.lin.ego.base.vo.EUTreeNode;
import com.lin.ego.manager.service.ItemCatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {


    @Override
    public List<EUTreeNode> getByParentId(Long parentId) {
        //第一步：构建返回对象
        List<EUTreeNode> nodes = new ArrayList<EUTreeNode>();
        //第二步：查询数据库获得数据
        EntityWrapper<ItemCat> wrapper = new EntityWrapper<ItemCat>();
        wrapper.eq("parent_id",parentId);
        List<ItemCat> itemCats = this.selectList(wrapper);
        for (ItemCat itemCat : itemCats) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(itemCat.getId());
            euTreeNode.setText(itemCat.getName());
            euTreeNode.setState("open");
            if(itemCat.getIsParent()==1){
                euTreeNode.setState("closed");
            }
            nodes.add(euTreeNode);

        }


        return nodes;
    }
}
