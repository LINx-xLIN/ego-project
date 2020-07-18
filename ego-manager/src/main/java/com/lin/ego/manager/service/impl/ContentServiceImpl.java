package com.lin.ego.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ContentMapper;
import com.lin.ego.base.pojo.Content;
import com.lin.ego.base.vo.EUDataGridResult;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Override
    public EUDataGridResult listByCatIdAndPage(Long categoryId, int page, int rows) {
        EUDataGridResult euDataGridResult = new EUDataGridResult();

        EntityWrapper<Content> wrapper = new EntityWrapper<>();
        wrapper.eq("category_id", categoryId);
        Page<Content> contentPage = this.selectPage(new Page<>(page, rows), wrapper);

        euDataGridResult.setTotal(contentPage.getTotal());
        euDataGridResult.setRows(contentPage.getRecords());
        return euDataGridResult;
    }

    @Override
    public EgoResult addContent(Content content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());

        this.insert(content);

        return EgoResult.ok();
    }

    @Override
    public EgoResult update(Content content) {

        this.updateById(content);

        return EgoResult.ok();
    }

    @Override
    public EgoResult delete(Integer[] ids) {
        List<Integer> idss = Arrays.asList(ids);
        this.deleteBatchIds(idss);
        return EgoResult.ok();
    }
}
