package com.carwash.washer_service.repository;


import com.carwash.washer_service.model.Washer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasherDAO extends JpaRepository<Washer, Long> {
    Object findAllById(Long washerId);
}




