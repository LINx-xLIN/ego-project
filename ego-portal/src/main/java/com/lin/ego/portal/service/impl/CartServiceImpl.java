package com.lin.ego.portal.service.impl;

import com.lin.ego.base.pojo.Item;
import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.utils.HttpClientUtils;
import com.lin.ego.base.vo.CartItem;
import com.lin.ego.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    @Value("${REST_BASE_URL}")
    private String restBasePath;
    private final static String EGO_CART_COOKIE = "EGO_CART_COOKIE";


    @Override
    public List<CartItem> addToCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        String jsonData = HttpClientUtils.doGet(restBasePath + "/item/" + itemId);
        Item item = JsonUtils.jsonToPojo(jsonData, Item.class);

        List<CartItem> cartItems = this.getCart(request);

        boolean itemExist=false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId().equals(itemId.toString())) {
                int total = cartItem.getNum() + num;
                cartItem.setNum(total);
                itemExist=true;
                break;
            }
        }

        if (!itemExist) {
            CartItem cartItem = new CartItem();
            cartItem.setNum(num);
            cartItem.setId(item.getId() + "");
            cartItem.setTitle(item.getTitle());
            cartItem.setImage(item.getImage());
            cartItem.setPrice(item.getPrice());

            cartItems.add(cartItem);
        }

        Cookie c = new Cookie(EGO_CART_COOKIE, JsonUtils.objectToJson(cartItems));
        c.setPath("/");
        response.addCookie(c);
        return cartItems;
    }

    @Override
    public List<CartItem> showCart(HttpServletRequest request) {
        return this.getCart(request);
    }

    @Override
    public List<CartItem> editCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> cartItems = this.getCart(request);
        for (CartItem cartItem : cartItems) {

            if (cartItem.getId().equals(itemId.toString())) {
                cartItem.setNum(num);
                break;
            }
        }

        Cookie c = new Cookie(EGO_CART_COOKIE, JsonUtils.objectToJson(cartItems));

        c.setPath("/");

        response.addCookie(c);
        return  cartItems;

    }

    @Override
    public List<CartItem> deleteCart(Long itemId, HttpServletRequest request, HttpServletResponse response) {

        List<CartItem> cartItems = this.getCart(request);
        for (int i=0;i<cartItems.size();i++){
            if (cartItems.get(i).getId().equals(itemId.toString())){
                //删除对应的商品
                cartItems.remove(i);
            }
        }

        Cookie c = new Cookie(EGO_CART_COOKIE, JsonUtils.objectToJson(cartItems));
        c.setPath("/");
        response.addCookie(c);

        return cartItems;

    }


    private List<CartItem> getCart(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean flag=false;
        List<CartItem>  cartItems=null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(EGO_CART_COOKIE)) {
                String value = cookie.getValue();
                cartItems = JsonUtils.jsonToList(value, CartItem.class);

                flag=true;
                break;
            }

        }

        if (!flag) {
             cartItems = new ArrayList<>();
        }

        return cartItems;
    }


}
