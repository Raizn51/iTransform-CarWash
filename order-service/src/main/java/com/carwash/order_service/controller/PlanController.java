package com.carwash.order_service.controller;


import com.carwash.order_service.dto.PlanDTO;
import com.carwash.order_service.model.Plan;
import com.carwash.order_service.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
public class PlanController {

    final private PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @Operation(summary = "Create a package (Admin only)")
    @PostMapping("/create")
    public ResponseEntity<PlanDTO> create(@Valid @RequestBody PlanDTO dto) {
        return ResponseEntity.ok(planService.createPackage(dto));
    }

    @Operation(summary = "Get all packages")
    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAll() {
        return ResponseEntity.ok(planService.getAllPackages());
    }

    @Operation(summary = "Get package by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getById(@PathVariable Long id) {
        return ResponseEntity.ok(planService.getPackageById(id));
    }
}
