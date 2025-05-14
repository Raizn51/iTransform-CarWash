package com.carwash.admin_service.client;

import com.carwash.admin_service.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="order-service",path = "/api/v1/orders")
public interface OrderServiceClient {

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id);


    @GetMapping
    public ResponseEntity<List<Order>> getAll();

}
