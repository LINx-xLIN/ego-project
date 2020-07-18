package com.lin.ego.portal.controller;

import com.lin.ego.base.vo.CartItem;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.OrderDetail;
import com.lin.ego.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrder(HttpServletRequest request) {
        List<CartItem> cartItems = orderService.showOrder(request);
        request.setAttribute("cartList", cartItems);
        return "order-cart";
    }

    @PostMapping(value = "/create")
    public String save(OrderDetail order, ModelMap map) {

        EgoResult egoResult = orderService.save(order);

        if (null != egoResult.getData() && !"".equals(egoResult.getData())) {
            map.addAttribute("orderId", egoResult.getData());
            map.addAttribute("payment", order.getPayment());

            Calendar instance = Calendar.getInstance();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/E");
            instance.add(Calendar.DATE, 3);

            System.out.println(simpleDateFormat.format(instance.getTime()));

            map.addAttribute("date", simpleDateFormat.format(instance.getTime()));


            return "success";

        }else {
            map.addAttribute("message", egoResult.getData());
            return "error/exception";
        }
    }
}
