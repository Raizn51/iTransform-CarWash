package com.carwash.order_service.repository;


import com.carwash.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Long> {

}
