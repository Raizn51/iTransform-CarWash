package com.carwash.auth_service.service.implementation;

import com.carwash.auth_service.dto.JWTAuthenticationResponse;
import com.carwash.auth_service.dto.RefreshTokenRequest;
import com.carwash.auth_service.dto.SignupRequest;
import com.carwash.auth_service.dto.SignInRequest;
import com.carwash.auth_service.model.AuthUser;
import com.carwash.auth_service.model.Role;
import com.carwash.auth_service.repository.AuthUserDAO;
import com.carwash.auth_service.service.AuthenticatationService;
import com.carwash.auth_service.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticatationService {

    private final AuthUserDAO authUserDAO;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public AuthUser signup(SignupRequest signupRequest)
    {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(signupRequest.getUsername());
        authUser.setFullName(signupRequest.getFullName());
        authUser.setEmail(signupRequest.getEmail());
        authUser.setRole(Role.CUSTOMER);
        authUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return authUserDAO.save(authUser);
    }

    @Override
    public JWTAuthenticationResponse signin(SignInRequest signinRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsernameOrEmail(), signinRequest.getPassword()));

        var user = authUserDAO.findByUsernameOrEmail(signinRequest.getUsernameOrEmail(),signinRequest.getUsernameOrEmail()).orElseThrow(()->new IllegalArgumentException("Invalid username or email"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JWTAuthenticationResponse JWTAuthenticationResponse = new JWTAuthenticationResponse();
        JWTAuthenticationResponse.setToken(jwt);
        JWTAuthenticationResponse.setRefreshToken(refreshToken);
        return JWTAuthenticationResponse;
    }

    @Override
    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
    {
        String usernameOrEmail= jwtService.extractUserName(refreshTokenRequest.getToken());
        AuthUser authUser = authUserDAO.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),authUser))
        {
            var jwt= jwtService.generateToken(authUser);

            JWTAuthenticationResponse JWTAuthenticationResponse = new JWTAuthenticationResponse();
            JWTAuthenticationResponse.setToken(jwt);
            JWTAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return JWTAuthenticationResponse;
        }
        return null;
    }
}
