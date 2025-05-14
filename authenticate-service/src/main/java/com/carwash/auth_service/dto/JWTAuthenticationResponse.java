package com.carwash.auth_service.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {

    private String token;

    private String refreshToken;


}