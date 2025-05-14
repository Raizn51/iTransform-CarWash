package com.carwash.order_service.service;


import com.carwash.order_service.dto.OrderDTO;
import com.carwash.order_service.model.Order;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO dto);
    List<OrderDTO> getAllOrders();
    Order getOrderById(Long id);

    void updateOrderStatus(Long orderId, String status);
}
