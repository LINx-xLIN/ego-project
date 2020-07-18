package com.lin.ego.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ContentCategoryMapper;
import com.lin.ego.base.pojo.ContentCategory;
import com.lin.ego.base.vo.EUTreeNode;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ContentCategoryService;
import com.sun.javafx.runtime.eula.Eula;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {


    @Override
    public List<EUTreeNode> selectByParentId(Long parentId) {
        List<EUTreeNode> euTreeNodes = new ArrayList<>();

        EntityWrapper<ContentCategory> wrapper = new EntityWrapper<ContentCategory>();
        wrapper.eq("parent_id", parentId);
        wrapper.eq("status", 1);
        List<ContentCategory> contentCategories = this.selectList(wrapper);
        for (ContentCategory contentCategory : contentCategories) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setText(contentCategory.getName());
            euTreeNode.setId(contentCategory.getId());
            euTreeNode.setState("open");
            if(contentCategory.getIsParent()==1){
                euTreeNode.setState("closed");
            }
            euTreeNodes.add(euTreeNode);
        }



        return euTreeNodes;
    }

    @Override
    @Transactional
    public EgoResult save(Long parentId, String name) {

        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setStatus(1L);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(contentCategory.getCreated());
        contentCategory.setIsParent(0L);
        this.insert(contentCategory);

        ContentCategory parentcontentCategory = this.selectById(parentId);
        if(parentcontentCategory.getIsParent() == 0L){
            parentcontentCategory.setIsParent(1L);
            this.updateById(parentcontentCategory);
        }


        return EgoResult.ok(contentCategory);
    }

    @Override
    public EgoResult updateNode(String name, Long id) {


        ContentCategory contentCategory = this.selectById(id);
        contentCategory.setName(name);
        contentCategory.setUpdated(new Date());
        this.updateById(contentCategory);
        return EgoResult.ok(contentCategory);


    }

    @Override
    @Transactional
    public EgoResult deleteNode(Long id, Long parentId) {


        ContentCategory contentCategory = this.selectById(id);
        if (parentId == null) {
            parentId = contentCategory.getParentId();
        }
        contentCategory.setStatus(2L);
        this.updateById(contentCategory);
        EntityWrapper<ContentCategory> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.eq("status", 1);
        List<ContentCategory> contentCategories = this.selectList(wrapper);
        if(contentCategories.size()==0){
            ContentCategory contentCategory1 = this.selectById(parentId);
            contentCategory1.setIsParent(0L);
            this.updateById(contentCategory1);
        }


        return EgoResult.ok(contentCategory);
    }
}
