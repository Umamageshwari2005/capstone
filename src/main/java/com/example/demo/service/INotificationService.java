package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.entity.Notification;

public interface INotificationService {

    Notification addNotification(NotificationDTO dto);

    Notification updateNotification(NotificationDTO dto);

    NotificationDTO getNotificationById(int id);

    void deleteNotification(int id);

    List<Notification> getAllNotifications();
}