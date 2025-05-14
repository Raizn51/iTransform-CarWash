package com.carwash.order_service.model;


import com.carwash.order_service.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long washerId;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Plan washPlan;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Use Enum for status

    private LocalDateTime scheduledDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Order(OrderDTO dto, Plan washPlan) {
        this.customerId = dto.getCustomerId();
        this.washerId = dto.getWasherId();
        this.washPlan = washPlan;
        this.status = dto.getStatus() != null ? dto.getStatus() : OrderStatus.PENDING;
        this.scheduledDate = dto.getScheduledDate();
    }
}
