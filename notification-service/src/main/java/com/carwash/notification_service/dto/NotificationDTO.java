package com.carwash.notification_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private String recipient;
    private String message;
    private String type; // INFO, WARNING, ERROR
    private LocalDateTime timestamp;
}
