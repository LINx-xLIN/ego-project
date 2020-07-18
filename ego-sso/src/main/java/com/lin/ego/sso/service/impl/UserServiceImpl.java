package com.lin.ego.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lin.ego.base.mapper.UserMapper;
import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.pojo.User;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.sso.service.UserService;
import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JedisCluster jedisCluster;

    @Value("${EGO_USER_TOKEN}")
    private String EGO_USER_TOKEN;

    @Value("${EGO_USER_KEY}")
    private String EGO_USER_KEY;

    @Value("${EGO_USER_EXPIRE_TIME}")
    private Integer EGO_USER_EXPIRE_TIME;


    @Override
    public EgoResult check(String param, Integer type) {

        HashMap<String, Object> params = new HashMap<>();
        if (type != null) {
            switch (type) {
                case 1:
                    params.put("username", param);
                    break;
                case 2:
                    params.put("phone", param);
                    break;
                case 3:
                    params.put("email", param);
                default:
                    return EgoResult.build(400, "待校验的数据的类型有误");
            }
        } else {
            return EgoResult.build(400, "待校验的数据的类型不能为空，请查找格式！");
        }

        List<User> users = this.selectByMap(params);


        if (users != null && users.size() > 0) {
            return EgoResult.ok(false);
        }

        return EgoResult.ok(true);


    }

    @Override
    public EgoResult register(User user) {

        try {
            user.setCreated(new Date());
            user.setUpdated(user.getCreated());
            this.insert(user);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();

            return EgoResult.build(400, null);
        }

    }

    @Override
    public EgoResult login(String username, String password, HttpServletResponse response) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        List<User> users = this.selectList(wrapper);
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(EGO_USER_TOKEN, token);

            /*?????*/
            cookie.setPath("/");
            response.addCookie(cookie);

            jedisCluster.set(EGO_USER_KEY+":"+token, JsonUtils.objectToJson(user));
            jedisCluster.expire(EGO_USER_KEY+":"+token,EGO_USER_EXPIRE_TIME);
            return EgoResult.ok(token);

        }


        return EgoResult.build(400,null);
    }

    @Override
    public EgoResult checkLogin(String token) {


        String jsonData = jedisCluster.get(EGO_USER_KEY + ":" + token);

        if (jsonData != null && !"".equals(jsonData)) {
            jedisCluster.expire(EGO_USER_KEY + ":" + token, EGO_USER_EXPIRE_TIME);

            return EgoResult.ok(JsonUtils.jsonToPojo(jsonData, User.class));

        }
        return EgoResult.build(400,"用户未登录或者登陆失效，请重新登陆");


    }
}
