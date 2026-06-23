package com.example.demo.dto;

public class ApplicationDTO {
	private int applicationId;
	private String status;
	
	public ApplicationDTO() {
		
	}
	public ApplicationDTO(int applicationId, String status) {
		super();
		this.applicationId = applicationId;
		this.status = status;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ApplicationDTO [applicationId=" + applicationId + ", status=" + status + "]";
	}
	
	
}
