package com.carwash.admin_service.controller;

import com.carwash.admin_service.dto.CustomerDTO;
import com.carwash.admin_service.dto.WasherDTO;
import com.carwash.admin_service.model.Customer;
import com.carwash.admin_service.model.Order;
import com.carwash.admin_service.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminController manages all admin-level operations such as managing customers, washers, and orders.
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Admin Controller", description = "Handles admin operations for customers, washers, and orders")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Get all registered customers.
     */
    @Operation(summary = "Get all customers", description = "Fetches a list of all registered customers.")
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(adminService.getAllCustomers());
    }

    /**
     * Get customer details by ID.
     */
    @Operation(summary = "Get customer by ID", description = "Fetches a customer using their unique ID.")
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getCustomerById(id));
    }

    /**
     * Update an existing customer's information.
     */
    @Operation(summary = "Update customer", description = "Updates the details of a specific customer.")
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(adminService.updateCustomer(id, dto));
    }

    /**
     * Deactivate a customer account.
     */
    @Operation(summary = "Deactivate customer", description = "Marks a customer account as inactive.")
    @PutMapping("/customers/{id}/deactivate")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable Long id) {
        adminService.deactivateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Reactivate a previously deactivated customer account.
     */
    @Operation(summary = "Activate customer", description = "Reactivates a customer account.")
    @PutMapping("/customers/{id}/activate")
    public ResponseEntity<Void> activateCustomer(@PathVariable Long id) {
        adminService.activateCustomer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all washer accounts.
     */
    @Operation(summary = "Get all washers", description = "Retrieves a list of all registered washers.")
    @GetMapping("/washers")
    public ResponseEntity<List<WasherDTO>> getAllWashers() {
        return ResponseEntity.ok(adminService.getAllWashers());
    }

    /**
     * Register a new washer.
     */
    @Operation(summary = "Add new washer", description = "Creates a new washer account.")
    @PostMapping("/washer")
    public ResponseEntity<WasherDTO> addNewWasher(@RequestBody WasherDTO washer) {
        return ResponseEntity.ok(adminService.addNewWasher(washer));
    }

    /**
     * Update existing washer details.
     */
    @Operation(summary = "Update washer", description = "Updates the information of an existing washer.")
    @PutMapping("/washer/{id}")
    public ResponseEntity<WasherDTO> updateWasher(@PathVariable Long id, @RequestBody WasherDTO washer) {
        return ResponseEntity.ok(adminService.updateWasher(id, washer));
    }

    /**
     * Get all orders in the system.
     */
    @Operation(summary = "Get all orders", description = "Retrieves all customer orders from the order service.")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(adminService.getAllOrders());
    }

    /**
     * Get details of a specific order by ID.
     */
    @Operation(summary = "Get order by ID", description = "Fetches the details of an order by its ID.")
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getOrderById(id));
    }
}
