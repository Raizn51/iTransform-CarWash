package com.carwash.order_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateEvent implements Serializable {
    private Long orderId;
    private String status;
}