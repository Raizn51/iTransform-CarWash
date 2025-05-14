package com.carwash.notification_service.service;

import com.carwash.notification_service.dto.NotificationDTO;
import com.carwash.notification_service.model.Notification;
import jakarta.validation.Valid;

import java.util.List;

public interface NotificationService {
    Notification sendNotification(@Valid NotificationDTO dto);

    List<Notification> getNotifications(String recipient);
}
