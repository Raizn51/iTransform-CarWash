package com.carwash.order_service.dto;

import com.carwash.order_service.model.OrderStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    private Long washerId;

    @NotNull
    private Long packageId;

    private OrderStatus status;

    @Future(message = "Scheduled date must be in the future")
    private LocalDateTime scheduledDate;



}
