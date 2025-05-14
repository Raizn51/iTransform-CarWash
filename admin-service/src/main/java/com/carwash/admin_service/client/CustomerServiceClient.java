package com.carwash.admin_service.client;

import com.carwash.admin_service.dto.CustomerDTO;
import com.carwash.admin_service.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service", path = "/api/v1")
public interface CustomerServiceClient {

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers();

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id);

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO);

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable Long id);


    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateCustomer(@PathVariable Long id);
}
