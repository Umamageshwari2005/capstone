package com.hexaware.career.service;

import java.util.List;

import com.hexaware.career.dto.NotificationDTO;
import com.hexaware.career.entity.Notification;

public interface INotificationService {

    Notification addNotification(NotificationDTO dto);

    Notification updateNotification(int notificationId, NotificationDTO dto);

    NotificationDTO getNotificationById(int notificationId);

    List<NotificationDTO> getNotificationsByUser(int userId);

    List<Notification> getAllNotifications();

    void deleteNotification(int notificationId);

}