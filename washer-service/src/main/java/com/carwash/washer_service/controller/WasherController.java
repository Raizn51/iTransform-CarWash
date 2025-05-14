package com.carwash.washer_service.controller;

import com.carwash.washer_service.dto.WasherDTO;
import com.carwash.washer_service.service.WasherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Washer Controller", description = "CRUD operations and profile/order management for Washers")
public class WasherController {

    @Autowired
    private WasherService washerService;

    @Operation(summary = "Create a new washer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Washer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid washer data")
    })
    @PostMapping
    public WasherDTO createWasher(@RequestBody @Valid WasherDTO washerDTO) {
        return washerService.createWasher(washerDTO);
    }

    @Operation(summary = "Get all washers")
    @ApiResponse(responseCode = "200", description = "List of washers retrieved")
    @GetMapping
    public List<WasherDTO> getAllWashers() {
        return washerService.getAllWashers();
    }

    @Operation(summary = "Get washer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Washer found"),
            @ApiResponse(responseCode = "404", description = "Washer not found")
    })
    @GetMapping("/{id}")
    public WasherDTO getWasherById(@PathVariable Long id) {
        return washerService.getWasherById(id);
    }

    @Operation(summary = "Update washer details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Washer updated"),
            @ApiResponse(responseCode = "404", description = "Washer not found")
    })
    @PutMapping("/{id}")
    public WasherDTO updateWasher(@PathVariable Long id, @RequestBody @Valid WasherDTO washerDTO) {
        return washerService.updateWasher(id, washerDTO);
    }

    @Operation(summary = "Delete washer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Washer deleted"),
            @ApiResponse(responseCode = "404", description = "Washer not found")
    })
    @DeleteMapping("/{id}")
    public void deleteWasher(@PathVariable Long id) {
        washerService.deleteWasher(id);
    }

    @Operation(summary = "Assign an order to washer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Washer not found")
    })
    @PostMapping("/{id}/orders/{orderId}")
    public WasherDTO assignOrderToWasher(@PathVariable Long id, @PathVariable Long orderId) {
        return washerService.assignOrder(id, orderId);
    }

    @Operation(summary = "Update washer profile image URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile image updated"),
            @ApiResponse(responseCode = "404", description = "Washer not found")
    })
    @PutMapping("/{id}/profile-image")
    public WasherDTO updateProfileImage(@PathVariable Long id, @RequestParam String imageUrl) {
        return washerService.updateProfileImage(id, imageUrl);
    }
}
