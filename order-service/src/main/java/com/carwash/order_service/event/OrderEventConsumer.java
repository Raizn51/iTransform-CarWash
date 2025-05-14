package com.carwash.order_service.event;

import com.carwash.order_service.config.RabbitMQConfig;
import com.carwash.order_service.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private final OrderService orderService;

    public OrderEventConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_PROCESSED_QUEUE)
    public void handlePaymentProcessed(PaymentProcessedEvent event) {
        String status = event.isSuccess() ? "PAYMENT_COMPLETED" : "PAYMENT_FAILED";
        orderService.updateOrderStatus(Long.valueOf(event.getOrderId()), status);
    }
}
