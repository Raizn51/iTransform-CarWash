package com.carwash.admin_service.client;

import com.carwash.admin_service.dto.WasherDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "washer-service", path = "/api/v1")
public interface WasherServiceClient {

    @PostMapping
    WasherDTO createWasher(@RequestBody @Valid WasherDTO washerDTO);

    @GetMapping
    List<WasherDTO> getAllWashers();

    @GetMapping("/{id}")
    WasherDTO getWasherById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    WasherDTO updateWasher(@PathVariable("id") Long id, @RequestBody @Valid WasherDTO washerDTO);

    @DeleteMapping("/{id}")
    void deleteWasher(@PathVariable("id") Long id);

    @PostMapping("/{id}/orders/{orderId}")
    WasherDTO assignOrderToWasher(@PathVariable("id") Long id, @PathVariable("orderId") Long orderId);
}
