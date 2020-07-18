package com.lin.ego.rest.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.ContentMapper;
import com.lin.ego.base.pojo.Content;
import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.sql.Wrapper;
import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    private JedisCluster jedisCluster;

    private final static String EGO_CONTENT = "EGO_CONTENT";


    @Override
    public EgoResult findContentByCategoryId(Long cid) {

        List<Content> contents = null;
        try {
            String contentJson = jedisCluster.hget(EGO_CONTENT, cid + "");
            contents = null;
            if (StringUtils.isEmpty(contentJson)) {
                EntityWrapper<Content> wrapper = new EntityWrapper<>();
                wrapper.eq("category_id", cid);
                contents = this.selectList(wrapper);

                jedisCluster.hset(EGO_CONTENT,cid+"", JsonUtils.objectToJson(contents));

            }else {

                contents = JsonUtils.jsonToList(contentJson,Content.class);
                System.out.println(contents);
            }


        } catch (Exception e) {
            e.printStackTrace();
            EntityWrapper<Content> wrapper = new EntityWrapper<>();
            wrapper.eq("category_id", cid);
            contents = this.selectList(wrapper);
        }
        return EgoResult.ok(contents);

    }
}
