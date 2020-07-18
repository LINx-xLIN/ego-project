package com.lin.ego.portal.service.impl;

import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.utils.HttpClientUtils;
import com.lin.ego.base.vo.CartItem;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.OrderDetail;
import com.lin.ego.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final static String EGO_CART_COOKIE = "EGO_CART_COOKIE";


    @Value("${ORDER_BASE_URL}")
    private String orderBaseURL;

    @Override
    public List<CartItem> showOrder(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        List<CartItem> cartItems = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(EGO_CART_COOKIE)) {
                String value = cookie.getValue();
                cartItems = JsonUtils.jsonToList(value, CartItem.class);
                flag = true;
                break;
            }
        }


        //1.Cookie没有任何数据，创建新的List
        if (flag == false) {
            cartItems = new ArrayList<>();
        }
        return cartItems;
    }

    @Override
    public EgoResult save(OrderDetail order) {

        String jsonData = JsonUtils.objectToJson(order);
        HashMap<String, String> params = new HashMap<>();
        params.put("order", jsonData);

        String resultJson = HttpClientUtils.doPost(orderBaseURL + "/create", params);

        if (resultJson != null) {
            EgoResult egoResult = JsonUtils.jsonToPojo(resultJson, EgoResult.class);

            Object orderId = egoResult.getData();
            if (orderId != null && !"".equals((orderId))) {
                return egoResult;
            }
        }


        return EgoResult.ok(null);
    }


}
