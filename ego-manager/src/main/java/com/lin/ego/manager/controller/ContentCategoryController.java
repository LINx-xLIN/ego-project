package com.lin.ego.manager.controller;

import com.lin.ego.base.vo.EUTreeNode;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping("/list")
    public List<EUTreeNode> selectParentId(@RequestParam(name="id",defaultValue = "0") Long parentId){
        return contentCategoryService.selectByParentId(parentId);
    }

    /**
     * 增加节点
     * @param parentId
     * @param name
     * @return
     */
    @PostMapping(value="/create")
    public EgoResult create(Long parentId, String name){
        EgoResult result = null;
        try {
            return contentCategoryService.save( parentId ,name);
        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.build(400, "增加节点出错");
        }

    }

    @PostMapping("/update")
    public EgoResult updateNode(Long id,String name){
        return contentCategoryService.updateNode(name, id);
    }

    @PostMapping("/delete")
    public EgoResult deleteNode(Long id,Long parentId){

        return contentCategoryService.deleteNode(id, parentId);
    }




}
