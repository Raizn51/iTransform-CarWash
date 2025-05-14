package com.carwash.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
public class AuthUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;  // Unique username for login

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;  // Hashed password

    @Column(unique = true, nullable = false)
    private String email;     // Unique email address

    @Enumerated(EnumType.STRING)
    private Role role;



    private boolean active = true;         // Whether account is active
    private LocalDateTime createdAt;      // When account was created
    private LocalDateTime updatedAt;      // When account was last updated

    // Set timestamps before persisting new entity
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Update timestamp before updating entity
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}