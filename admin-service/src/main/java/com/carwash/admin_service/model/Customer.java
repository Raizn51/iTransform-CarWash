package com.carwash.admin_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;  // Changed to Long for consistency

    private String userName;

    private String fullName;

    private String password;  // Will be hashed

    private String email;

    private String phoneNumber;
    private String profileImageUrl;

    private boolean isActive = true;

    private List<Long> carIds = new ArrayList<>();

    private List<Long> orderIds = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}