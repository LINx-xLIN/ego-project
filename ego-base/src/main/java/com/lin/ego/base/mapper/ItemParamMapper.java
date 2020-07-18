package com.lin.ego.base.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lin.ego.base.pojo.ItemParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ItemParamMapper extends BaseMapper<ItemParam> {



    @Select("select p.id,p.item_cat_id as itemCatId,t.name as itemCatName,p.param_data as paramData,p.created,p.updated "
            + "from tb_item_param p left join tb_item_cat t on p.item_cat_id = t.id "
            + "limit ${start},${pageSize}")
    List<Map<String,Object>> ListAndPage(@Param("start") int start, @Param("pageSize") int pageSize);

}
