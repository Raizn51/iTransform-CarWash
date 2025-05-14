package com.carwash.controller;

import com.carwash.dto.CustomerDTO;
import com.carwash.model.Customer;
import com.carwash.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.registerCustomer(customerDTO));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable Long id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateCustomer(@PathVariable Long id) {
        customerService.activateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/cars/{carId}")
    public ResponseEntity<Void> addCarToCustomer(
            @PathVariable Long customerId,
            @PathVariable Long carId) {
        customerService.addCarToCustomer(customerId, carId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{customerId}/orders/{orderId}")
    public ResponseEntity<Void> addOrderToCustomer(
            @PathVariable Long customerId,
            @PathVariable Long orderId) {
        customerService.addOrderToCustomer(customerId, orderId);
        return ResponseEntity.ok().build();
    }
}
