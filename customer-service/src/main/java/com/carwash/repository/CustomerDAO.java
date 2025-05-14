package com.carwash.repository;

import com.carwash.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}