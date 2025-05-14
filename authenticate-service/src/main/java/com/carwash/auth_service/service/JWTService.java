package com.carwash.auth_service.service;

import com.carwash.auth_service.model.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {

    String generateToken(UserDetails userDetails);


    String generateRefreshToken(HashMap<String, Object> extraClaims, UserDetails userDetails);

    String extractUserName(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);


}
