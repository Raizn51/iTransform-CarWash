package com.carwash.payment_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    private Long id;

    private String packageName;
    private String description;
    private Double price;
    private String createdBy; // Admin


}
