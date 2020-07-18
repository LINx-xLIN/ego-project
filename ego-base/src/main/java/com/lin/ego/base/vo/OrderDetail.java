package com.lin.ego.base.vo;

import com.lin.ego.base.pojo.Order;
import com.lin.ego.base.pojo.OrderItem;
import com.lin.ego.base.pojo.OrderShipping;

import java.util.List;

public class OrderDetail extends Order {
    //一个订单可以有多个订单项，所以使用集合
    private List<OrderItem> orderItems;
    //一个订单只有一份发送地址，所以使用引用
    private OrderShipping orderShipping;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
