package com.lin.ego.rest.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lin.ego.base.mapper.ItemCatMapper;
import com.lin.ego.base.pojo.ItemCat;
import com.lin.ego.base.pojo.Menu;
import com.lin.ego.base.pojo.MenuNode;
import com.lin.ego.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {


    @Autowired
    private ItemCatMapper itemCatMapper;


    @Override
    public Menu initMenu() {






        Menu menu = new Menu();

        menu.setData(getNodeByParantId(0L));

        return menu;
    }


    private List getNodeByParantId(Long parentId) {


        List menuNodes = new ArrayList<>();



        EntityWrapper<ItemCat> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<ItemCat> itemCats = itemCatMapper.selectList(wrapper);

        for (ItemCat itemCat : itemCats) {
            MenuNode menuNode = new MenuNode();
            menuNode.setN(itemCat.getName());
            menuNode.setU("/products/"+itemCat.getId()+".html");
            if(itemCat.getIsParent()==1){
                List node = getNodeByParantId(itemCat.getId());
                menuNode.setI(node);
                menuNodes.add(menuNode);
            }else {



                menuNodes.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());


            }


        }
        return menuNodes;


    }


}
