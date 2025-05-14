package com.carwash.order_service.model;


import com.carwash.order_service.dto.PlanDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "packages")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String packageName;
    private String description;
    private Double price;
    private String createdBy; // Admin


    // Inside Plan.java

    public Plan(PlanDTO dto) {
        this.packageName = dto.getPackageName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.createdBy = dto.getCreatedBy();
    }

}
