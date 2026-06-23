package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="notifications")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationId;
	private String message;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Notification() {
		
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", message=" + message + "]";
	}
	
	
}
