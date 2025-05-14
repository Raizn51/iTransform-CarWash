package com.carwash.order_service.event;

import com.carwash.order_service.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent implements Serializable {
    private Long orderId;
    private Long customerId;
    private Long washerId;
    private Long packageId;
    private OrderStatus status;
    private LocalDateTime scheduledDate;
}
