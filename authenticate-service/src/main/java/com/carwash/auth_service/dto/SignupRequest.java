package com.carwash.auth_service.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;

    private String fullName;

    private String email;

    private String password;
}