package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="jobPreference")
public class JobPreference {
           
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int preferenceId;
	private String preferredRole;
	private String location;
	@OneToOne(mappedBy = "preference")
	private User user;
	public JobPreference() {
		
	}
	public int getPreferenceId() {
		return preferenceId;
	}
	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}
	public String getPreferredRole() {
		return preferredRole;
	}
	public void setPreferredRole(String preferredRole) {
		this.preferredRole = preferredRole;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "JobPreference [preferenceId=" + preferenceId + ", preferredRole=" + preferredRole + ", location="
				+ location + "]";
	}
	
	
}
