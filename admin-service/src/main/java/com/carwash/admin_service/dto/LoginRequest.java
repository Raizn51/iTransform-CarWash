package com.carwash.admin_service.dto;


import lombok.Data;

@Data
public class LoginRequest {

    private String usernameOrEmail;

    private String password;


}