package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String fullName;
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="preference_id")
	@JsonIgnore
	private JobPreference preference;
	
	@OneToMany(mappedBy="user",
	           cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Resume> resumes;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	        name="user_skills",
	        joinColumns=@JoinColumn(name="user_id"),
	        inverseJoinColumns=@JoinColumn(name="skill_id")
	)
	@JsonIgnore
	private List<Skill> skills;
	@OneToMany(mappedBy="user",
	           cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Application> applications;
	@OneToMany(mappedBy="employer",
	           cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Job> jobs;
	@OneToMany(mappedBy="user",
	           cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Notification> notifications;
	
	public User() {
		
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public JobPreference getPreference() {
		return preference;
	}
	public void setPreference(JobPreference preference) {
		this.preference = preference;
	}
	
	public List<Resume> getResumes() {
		return resumes;
	}
	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + "]";
	}
	
	
}
