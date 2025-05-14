package com.carwash.auth_service.controller;


import com.carwash.auth_service.dto.JWTAuthenticationResponse;
import com.carwash.auth_service.dto.RefreshTokenRequest;
import com.carwash.auth_service.dto.SignupRequest;
import com.carwash.auth_service.dto.SignInRequest;
import com.carwash.auth_service.model.AuthUser;
import com.carwash.auth_service.service.AuthenticatationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticatationService authenticatationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthUser> signup(@RequestBody SignupRequest signupRequest)
    {
        System.out.println("Received request"+ signupRequest.getUsername());
        return  ResponseEntity.ok(authenticatationService.signup(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponse> signin(@RequestBody SignInRequest signinRequest)
    {
        JWTAuthenticationResponse jwtAuthenticationResponse = null;
        try {
            

             jwtAuthenticationResponse = authenticatationService.signin(signinRequest);
        } catch (Exception e) {
            System.out.println("Exception"+ e.getMessage());
        }

        return ResponseEntity.ok(jwtAuthenticationResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        return ResponseEntity.ok(authenticatationService.refreshToken(refreshTokenRequest));
    }



}