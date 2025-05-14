package com.carwash.model;

import com.carwash.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed to Long for consistency

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;  // Will be hashed

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;
    private String profileImageUrl;

    private boolean isActive = true;

    @ElementCollection
    private List<Long> carIds = new ArrayList<>();

    @ElementCollection
    private List<Long> orderIds = new ArrayList<>();

    // Audit fields
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

    // Constructor from DTO
    public Customer(CustomerDTO customerDTO) {
        this.userName = customerDTO.getUserName();
        this.fullName = customerDTO.getFullName();
        this.password = customerDTO.getPassword(); // Will be hashed in service
        this.email = customerDTO.getEmail();
        this.phoneNumber = customerDTO.getPhoneNumber();
        this.profileImageUrl = customerDTO.getProfileImageUrl();
    }
}