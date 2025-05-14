package com.carwash.notification_service.controller;

import com.carwash.notification_service.dto.NotificationDTO;
import com.carwash.notification_service.model.Notification;
import com.carwash.notification_service.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Notification Service")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping
    public ResponseEntity<Notification> send(@RequestBody @Valid NotificationDTO dto) {
        return new ResponseEntity<>(service.sendNotification(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{recipient}")
    public ResponseEntity<List<Notification>> getByRecipient(@PathVariable String recipient) {
        return ResponseEntity.ok(service.getNotifications(recipient));
    }
}
