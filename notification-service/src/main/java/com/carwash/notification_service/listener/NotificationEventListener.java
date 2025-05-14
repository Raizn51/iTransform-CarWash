package com.carwash.notification_service.listener;

import com.carwash.notification_service.dto.NotificationDTO;
import com.carwash.notification_service.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {

    @Autowired
    private NotificationService service;

    @RabbitListener(queues = "notification-queue")
    public void consumeNotification(NotificationDTO dto) {
        service.sendNotification(dto);
        System.out.println("Notification sent to: " + dto.getRecipient());
    }
}
