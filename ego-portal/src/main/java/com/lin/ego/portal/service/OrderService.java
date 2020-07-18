package com.lin.ego.portal.service;

import com.lin.ego.base.vo.CartItem;
import com.lin.ego.base.vo.EgoResult;
import com.lin.ego.base.vo.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    List<CartItem> showOrder(HttpServletRequest request);


    EgoResult save(OrderDetail order);
}
