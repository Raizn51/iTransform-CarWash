package com.carwash.payment_service.model;


import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Long customerId;

    private Long washerId;


    private Plan washPlan;


    private LocalDateTime scheduledDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
