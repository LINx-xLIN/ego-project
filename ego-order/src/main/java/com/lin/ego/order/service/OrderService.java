package com.lin.ego.order.service;

import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.OrderDetail;

public interface OrderService {
    EgoResult save(OrderDetail orderDetail);
}
