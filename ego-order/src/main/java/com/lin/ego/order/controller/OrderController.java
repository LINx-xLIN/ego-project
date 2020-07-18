package com.lin.ego.order.controller;

import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.OrderDetail;
import com.lin.ego.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/create")
    public EgoResult save(String order) {
        try {
            OrderDetail orderDetail = JsonUtils.jsonToPojo(order, OrderDetail.class);
            return orderService.save(orderDetail);
        } catch (Exception e) {
            e.printStackTrace();

            return EgoResult.build(400, null);
        }
    }
}
