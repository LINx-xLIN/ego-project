package com.lin.ego.portal.controller;


import com.lin.ego.base.vo.CartItem;
import com.lin.ego.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/add/{num}/{itemId}")
    public String add(@PathVariable("itemId") Long itemId, @PathVariable("num") Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> cartItems = cartService.addToCart(itemId, num, request, response);
        request.setAttribute("cartList", cartItems);
        return "cart";
    }

    @GetMapping("/cart")
    public String showCart(HttpServletRequest request) {
        List<CartItem> cartItems = cartService.showCart(request);
        request.setAttribute("cartList", cartItems);
        return "cart";
    }


    @PostMapping("/update/num/{itemId}/{num}")
    public String update(@PathVariable("itemId") Long itemId, @PathVariable("num") Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> cart = cartService.editCart(itemId, num, request, response);
        request.setAttribute("cartList", cart);
        return "cart";
    }

    @GetMapping("/delete/{itemId}")
    public String delete(@PathVariable("itemId") Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> cart = cartService.deleteCart(itemId, request, response);
        request.setAttribute("cartList", cart);
        return "cart";
    }
}
