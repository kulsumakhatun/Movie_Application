package org.niit.service;

import org.niit.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceimpl implements NotificationService{

    NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceimpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
