package com.lin.ego.base.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.vo.SearchItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item> {

    @Select("select i.id,i.sell_point as sellPoint,i.title,i.price,i.image,c.name as categoryName from tb_item i left join tb_item_cat c on i.cid = c.id where i.status=1")
    List<SearchItem> gatherDate();
}
