package com.carwash.order_service.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String packageName;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Double price;

    @NotBlank
    private String createdBy;
}
