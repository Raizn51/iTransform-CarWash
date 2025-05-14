package com.carwash.order_service.repository;


import com.carwash.order_service.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanDAO extends JpaRepository<Plan, Long> {}




