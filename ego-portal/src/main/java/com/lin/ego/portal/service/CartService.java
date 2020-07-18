package com.lin.ego.portal.service;

import com.lin.ego.base.vo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    List<CartItem> addToCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);


    List<CartItem> showCart(HttpServletRequest request);

    List<CartItem> editCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> deleteCart(Long itemId, HttpServletRequest request, HttpServletResponse response);


}
