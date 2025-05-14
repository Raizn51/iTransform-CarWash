package com.carwash.payment_service.config;

import com.carwash.payment_service.model.Plan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service",contextId = "PlanClient",path = "/api/v1/plans")
public interface PlanClient {

    @GetMapping("/{id}")
    ResponseEntity<Plan> getByPlanId(@PathVariable Long id);
}
