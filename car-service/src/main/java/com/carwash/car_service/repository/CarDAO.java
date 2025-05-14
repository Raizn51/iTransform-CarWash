package com.carwash.car_service.repository;

import com.carwash.car_service.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarDAO extends JpaRepository<Car,Integer> {
    Optional<Object> findByLicensePlate(String licensePlate);

}
