package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.entity.Notification;
import com.example.demo.exception.NotificationNotFoundException;
import com.example.demo.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    NotificationRepository repo;

    @Override
    public Notification addNotification(NotificationDTO dto) {

        Notification notification = new Notification();

        notification.setNotificationId(dto.getNotificationId());
        notification.setMessage(dto.getMessage());

        return repo.save(notification);
    }

    @Override
    public Notification updateNotification(NotificationDTO dto) {

        Notification notification = new Notification();

        notification.setNotificationId(dto.getNotificationId());
        notification.setMessage(dto.getMessage());

        return repo.save(notification);
    }

    @Override
    public NotificationDTO getNotificationById(int id) {

    	 Notification notification = repo.findById(id)
    	            .orElseThrow(() ->
    	            new NotificationNotFoundException(
    	                    "Notification Not Found With Id : " + id));
        NotificationDTO dto = new NotificationDTO();

        dto.setNotificationId(notification.getNotificationId());
        dto.setMessage(notification.getMessage());

        return dto;
    }

    @Override
    public void deleteNotification(int id) {
    	
    	Notification notification = repo.findById(id)
                .orElseThrow(() ->
                new NotificationNotFoundException(
                        "Notification Not Found With Id : " + id));
        repo.deleteById(id);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return repo.findAll();
    }
}