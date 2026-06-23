package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.entity.Notification;
import com.example.demo.service.INotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    INotificationService service;

    @PostMapping("/add")
    public Notification addNotification(@RequestBody NotificationDTO dto) {

        return service.addNotification(dto);
    }

    @PutMapping("/update")
    public Notification updateNotification(@RequestBody NotificationDTO dto) {

        return service.updateNotification(dto);
    }

    @GetMapping("/getbyid/{id}")
    public NotificationDTO getNotificationById(@PathVariable int id) {

        return service.getNotificationById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNotification(@PathVariable int id) {

        service.deleteNotification(id);

        return "Notification Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<Notification> getAllNotifications() {

        return service.getAllNotifications();
    }
}