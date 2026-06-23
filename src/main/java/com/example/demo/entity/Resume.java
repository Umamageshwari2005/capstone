package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="resumes")
public class Resume {
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	private String resumeName;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Resume() {
		
	}
	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Resume [resumeId=" + resumeId + ", resumeName=" + resumeName + "]";
	}
	
}
