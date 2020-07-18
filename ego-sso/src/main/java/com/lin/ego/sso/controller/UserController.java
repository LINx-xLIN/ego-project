package com.lin.ego.sso.controller;

import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.pojo.User;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.callback.Callback;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    public Object check(@PathVariable("param") String param, @PathVariable("type") Integer type, String callback) {
        EgoResult egoResult = userService.check(param, type);

        if (null == callback || "".equals(callback)) {
            return egoResult;
        } else {
            String jsonData = JsonUtils.objectToJson(egoResult);

            String jsData = callback + "(" + jsonData + ")";

            return jsData;

        }

    }

    @RequestMapping("/register")
    public EgoResult register(User user) {
        EgoResult result = userService.register(user);

        return result;

    }

    @RequestMapping("/login")
    public EgoResult login(String username, String password, HttpServletResponse response) {
        EgoResult result = userService.login(username, password, response);
        return result;
    }


    @RequestMapping("/token/{token}")
    public EgoResult checkLogin(@PathVariable("token") String token) {
        return userService.checkLogin(token);
    }
}
