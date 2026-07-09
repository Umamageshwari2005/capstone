package com.hexaware.career.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.career.dto.NotificationDTO;
import com.hexaware.career.entity.Notification;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.NotificationNotFoundException;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.NotificationRepository;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationServiceImpl implements INotificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository) {

        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notification addNotification(NotificationDTO dto) {

        logger.info("Creating Notification");

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        Notification notification = new Notification();

        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUser(user);

        Notification savedNotification =
                notificationRepository.save(notification);

        logger.info("Notification Created Successfully");

        return savedNotification;
    }

    @Override
    public Notification updateNotification(int notificationId,
                                           NotificationDTO dto) {

        logger.info("Updating Notification");

        Notification notification =
                notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification Not Found"));

        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setRead(dto.isRead());

        Notification updated =
                notificationRepository.save(notification);

        logger.info("Notification Updated Successfully");

        return updated;
    }

    @Override
    public NotificationDTO getNotificationById(int notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification Not Found"));

        NotificationDTO dto = new NotificationDTO();

        dto.setNotificationId(notification.getNotificationId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setUserId(notification.getUser().getUserId());

        return dto;
    }

    @Override
    public List<NotificationDTO> getNotificationsByUser(int userId) {

        List<Notification> notifications =
                notificationRepository.findByUserUserId(userId);

        List<NotificationDTO> list = new ArrayList<>();

        for (Notification notification : notifications) {

            NotificationDTO dto = new NotificationDTO();

            dto.setNotificationId(notification.getNotificationId());
            dto.setMessage(notification.getMessage());
            dto.setType(notification.getType());
            dto.setRead(notification.isRead());
            dto.setCreatedAt(notification.getCreatedAt());
            dto.setUserId(userId);

            list.add(dto);
        }

        return list;
    }

    @Override
    public List<Notification> getAllNotifications() {

        return notificationRepository.findAll();
    }

    @Override
    public void deleteNotification(int notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification Not Found"));

        notificationRepository.delete(notification);

        logger.info("Notification Deleted Successfully");
    }

}