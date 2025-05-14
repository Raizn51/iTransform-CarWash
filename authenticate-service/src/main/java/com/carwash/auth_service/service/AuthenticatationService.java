package com.carwash.auth_service.service;

import com.carwash.auth_service.dto.JWTAuthenticationResponse;
import com.carwash.auth_service.dto.RefreshTokenRequest;
import com.carwash.auth_service.dto.SignupRequest;
import com.carwash.auth_service.dto.SignInRequest;
import com.carwash.auth_service.model.AuthUser;

public interface AuthenticatationService {
    AuthUser signup(SignupRequest signupRequest);

    JWTAuthenticationResponse signin(SignInRequest signinRequest);

    JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
