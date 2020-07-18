package com.lin.ego.order.service.impl;

import com.lin.ego.base.mapper.OrderItemMapper;
import com.lin.ego.base.mapper.OrderMapper;
import com.lin.ego.base.mapper.OrderShippingMapper;
import com.lin.ego.base.pojo.OrderItem;
import com.lin.ego.base.pojo.OrderShipping;
import com.lin.ego.base.utils.IDUtils;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.OrderDetail;
import com.lin.ego.order.service.OrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderShippingMapper orderShippingMapper;


    @Override
    public EgoResult save(OrderDetail orderDetail) {

        long orderId = IDUtils.genItemId();

        Date now = new Date();
        orderDetail.setOrderId(orderId+"");
        orderDetail.setCreateTime(now);
        orderDetail.setUpdateTime(now);
        orderDetail.setStatus(1);
        orderMapper.insert(orderDetail);


        OrderShipping orderShipping = orderDetail.getOrderShipping();
        orderShipping.setCreated(now);
        orderShipping.setUpdated(now);
        orderShipping.setOrderId(orderId+"");
        orderShippingMapper.insert(orderShipping);


        List<OrderItem> orderItems = orderDetail.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setId(IDUtils.genItemId()+"");
            orderItem.setOrderId(orderId+"");
            orderItemMapper.insert(orderItem);
        }


        return EgoResult.ok(orderId);





    }
}
