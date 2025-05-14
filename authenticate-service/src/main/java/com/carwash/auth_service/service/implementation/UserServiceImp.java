package com.carwash.auth_service.service.implementation;

import com.carwash.auth_service.repository.AuthUserDAO;
import com.carwash.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    final private AuthUserDAO authUserDAO;

    @Override
    public UserDetailsService userDetailService()
    {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
            {
                return authUserDAO.findByUsernameOrEmail(username,username)
                        .orElseThrow(()-> new UsernameNotFoundException("User not found")) ;
            }
        };
    }


}