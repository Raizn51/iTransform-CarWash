package com.carwash.auth_service.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {


    UserDetailsService userDetailService();
}

