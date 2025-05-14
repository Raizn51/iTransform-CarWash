package com.carwash.auth_service.repository;

import com.carwash.auth_service.model.AuthUser;
import com.carwash.auth_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthUserDAO extends JpaRepository<AuthUser,Long>
{

    Optional<AuthUser> findByUsername(String username);
    Optional<AuthUser> findByUsernameOrEmail(String username, String email);

    AuthUser findByRole(Role role);
}
