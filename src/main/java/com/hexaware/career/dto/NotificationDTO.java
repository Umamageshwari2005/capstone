package com.hexaware.career.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationDTO {

    private int notificationId;

    @NotBlank(message = "Message is required")
    private String message;

    @NotBlank(message = "Notification Type is required")
    private String type;

    private boolean isRead;

    private LocalDateTime createdAt;

    @NotNull(message = "User Id is required")
    private Integer userId;

    public NotificationDTO() {

    }

    public NotificationDTO(int notificationId,
                           String message,
                           String type,
                           boolean isRead,
                           LocalDateTime createdAt,
                           Integer userId) {

        this.notificationId = notificationId;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NotificationDTO [notificationId=" + notificationId
                + ", message=" + message
                + ", type=" + type
                + ", isRead=" + isRead
                + ", createdAt=" + createdAt
                + ", userId=" + userId + "]";
    }

}