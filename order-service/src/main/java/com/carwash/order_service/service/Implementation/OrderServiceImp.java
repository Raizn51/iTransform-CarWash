package com.carwash.order_service.service.Implementation;

import com.carwash.order_service.dto.OrderDTO;
import com.carwash.order_service.event.OrderStatusUpdateEvent;
import com.carwash.order_service.exception.ResourceNotFoundException;
import com.carwash.order_service.model.*;
import com.carwash.order_service.model.Plan;
import com.carwash.order_service.repository.*;
import com.carwash.order_service.service.OrderService;
import com.carwash.order_service.config.RabbitMQConfig;
import com.carwash.order_service.event.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderDAO orderDAO;
    private final PlanDAO planDAO;
    private final RabbitTemplate rabbitTemplate;

    public OrderServiceImp(OrderDAO orderDAO, PlanDAO planDAO, RabbitTemplate rabbitTemplate) {
        this.orderDAO = orderDAO;
        this.planDAO = planDAO;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Plan pack = planDAO.findById(dto.getPackageId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        Order order = new Order(dto, pack);
        order = orderDAO.save(order);
        dto.setId(order.getId());

        // Publish OrderCreatedEvent
        publishOrderCreatedEvent(order);

        return dto;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderDAO.findAll().stream()
                .map(order -> new OrderDTO(order.getId(), order.getCustomerId(),
                        order.getWasherId(), order.getWashPlan().getId(),
                        order.getStatus(), order.getScheduledDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return order;
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderDAO.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(OrderStatus.valueOf(status));
        orderDAO.save(order);

        // Publish OrderStatusUpdateEvent
        publishOrderStatusUpdateEvent(orderId, status);
    }

    private void publishOrderCreatedEvent(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getCustomerId(),
                order.getWasherId(),
                order.getWashPlan().getId(),
                order.getStatus(),
                order.getScheduledDate()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                "order.created",
                event
        );
    }

    private void publishOrderStatusUpdateEvent(Long orderId, String status) {
        OrderStatusUpdateEvent event = new OrderStatusUpdateEvent(orderId, status);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                "order.status.update",
                event
        );
    }
}