package com.carwash.order_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessedEvent implements Serializable {
    private String orderId;
    private boolean success;
}
