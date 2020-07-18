package com.lin.ego.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.lin.ego.base.pojo.User;
import com.lin.ego.base.vo.EgoResult;

import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {


    EgoResult check(String param, Integer type);

    EgoResult register(User user);

    EgoResult login(String username, String password, HttpServletResponse response);

    EgoResult checkLogin(String token);



}
