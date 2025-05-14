package com.carwash.payment_service.config;

import com.carwash.payment_service.model.Order;
import com.carwash.payment_service.model.Plan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="order-service",contextId = "OrderServiceClient",path = "/api/v1/orders")
public interface OrderServiceClient {

    @GetMapping("/{id}")
    public ResponseEntity<Order> getByOrderId(@PathVariable Long id);

}

