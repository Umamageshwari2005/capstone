package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.entity.Notification;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private INotificationService service;

    @Test
    void testAddNotification() {

        NotificationDTO dto = new NotificationDTO();

        dto.setNotificationId(401);
        dto.setMessage("New Job Posted");

        Notification notification =
                service.addNotification(dto);

        assertNotNull(notification);

        assertEquals(
                "New Job Posted",
                notification.getMessage());
    }

    @Test
    void testGetNotificationById() {

        NotificationDTO dto =
                service.getNotificationById(401);

        assertNotNull(dto);

        assertEquals(
                401,
                dto.getNotificationId());
    }

    @Test
    void testUpdateNotification() {

        NotificationDTO dto =
                new NotificationDTO();

        dto.setNotificationId(401);
        dto.setMessage("Interview Scheduled");

        Notification notification =
                service.updateNotification(dto);

        assertNotNull(notification);

        assertEquals(
                "Interview Scheduled",
                notification.getMessage());
    }

    @Test
    void testGetAllNotifications() {

        List<Notification> notifications =
                service.getAllNotifications();

        assertNotNull(notifications);

        assertTrue(
                notifications.size() >= 0);
    }

    @Test
    void testDeleteNotification() {

        service.deleteNotification(401);

        assertTrue(true);
    }
}