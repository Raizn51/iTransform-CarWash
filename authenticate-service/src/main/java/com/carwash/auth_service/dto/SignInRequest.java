package com.carwash.auth_service.dto;


import lombok.Data;

@Data
public class SignInRequest {

    private String usernameOrEmail;

    private String password;


}