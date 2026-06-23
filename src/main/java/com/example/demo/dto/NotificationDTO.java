package com.example.demo.dto;

public class NotificationDTO {
	private int notificationId;
	private String message;
	public NotificationDTO() {
		
	}
	public NotificationDTO(int notificationId, String message) {
		super();
		this.notificationId = notificationId;
		this.message = message;
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
	@Override
	public String toString() {
		return "NotificationDTO [notificationId=" + notificationId + ", message=" + message + "]";
	}
	
	
}
