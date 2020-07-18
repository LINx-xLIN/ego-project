package com.lin.ego.portal.interceptor;

import com.lin.ego.base.pojo.User;
import com.lin.ego.base.utils.HttpClientUtils;
import com.lin.ego.base.vo.EgoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private static final String EGO_USER_TOKEN="EGO_USER_TOKEN";

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(EGO_USER_TOKEN)) {
                String token = cookie.getValue();
                String jsonData = HttpClientUtils.doGet(SSO_BASE_URL + "/token/" + token);
                EgoResult result = EgoResult.formatToPojo(jsonData, User.class);
                request.setAttribute("loginUser", result.getData());
                return true;
            }
        }

        response.sendRedirect(SSO_BASE_URL + "/showLogin");

        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
