package com.carwash.notification_service.service;

import com.carwash.notification_service.dto.NotificationDTO;
import com.carwash.notification_service.model.Notification;
import com.carwash.notification_service.repository.NotificationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository repo;

    @Override
    public Notification sendNotification(@Valid NotificationDTO dto) {
        Notification n = new Notification(null, dto.getRecipient(), dto.getMessage(), dto.getType(), LocalDateTime.now());
        return repo.save(n);
    }

    @Override
    public List<Notification> getNotifications(String recipient) {
        return repo.findByRecipient(recipient);
    }
}
