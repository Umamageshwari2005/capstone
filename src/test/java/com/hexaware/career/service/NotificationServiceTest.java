package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.NotificationDTO;
import com.hexaware.career.entity.Notification;
import com.hexaware.career.service.INotificationService;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private INotificationService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testAddNotification() {

        NotificationDTO dto = new NotificationDTO();

        dto.setUserId(1);
        dto.setMessage("New Job Posted");
        dto.setType("JOB");
        dto.setRead(false);

        Notification notification =
                service.addNotification(dto);

        assertNotNull(notification);
        assertEquals("New Job Posted",
                notification.getMessage());
    }

    @Test
    void testGetNotificationById() {

        NotificationDTO dto =
                service.getNotificationById(1);

        assertNotNull(dto);
    }

    @Test
    void testUpdateNotification() {

        NotificationDTO dto =
                new NotificationDTO();

        dto.setMessage("Interview Scheduled");
        dto.setType("INTERVIEW");
        dto.setRead(true);

        Notification notification =
                service.updateNotification(1, dto);

        assertNotNull(notification);

        assertEquals(
                "Interview Scheduled",
                notification.getMessage());
    }

    @Test
    void testGetNotificationsByUser() {

        List<NotificationDTO> notifications =
                service.getNotificationsByUser(1);

        assertNotNull(notifications);
    }

    @Test
    void testGetAllNotifications() {

        List<Notification> notifications =
                service.getAllNotifications();

        assertNotNull(notifications);
    }

    @Test
    void testDeleteNotification() {

        service.deleteNotification(1);

        assertTrue(true);
    }

}