package com.hexaware.career.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.dto.NotificationDTO;
import com.hexaware.career.entity.Notification;
import com.hexaware.career.service.INotificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final INotificationService service;

    public NotificationController(INotificationService service) {
        this.service = service;
    }

    // Send Notification
    @PostMapping
    public ResponseEntity<Notification> addNotification(
            @Valid @RequestBody NotificationDTO dto) {

        return new ResponseEntity<>(
                service.addNotification(dto),
                HttpStatus.CREATED);
    }

    // Update Notification
    @PutMapping("/{notificationId}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable int notificationId,
            @Valid @RequestBody NotificationDTO dto) {

        return ResponseEntity.ok(
                service.updateNotification(notificationId, dto));
    }

    // Get Notification By Id
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationDTO> getNotificationById(
            @PathVariable int notificationId) {

        return ResponseEntity.ok(
                service.getNotificationById(notificationId));
    }

    // Get Notifications By User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUser(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                service.getNotificationsByUser(userId));
    }

    // Delete Notification
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(
            @PathVariable int notificationId) {

        service.deleteNotification(notificationId);

        return ResponseEntity.ok("Notification Deleted Successfully");
    }

    // Get All Notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {

        return ResponseEntity.ok(
                service.getAllNotifications());
    }

}