package com.example.demo.dto;

public class JobDTO {
	private int jobId;
	private String jobTitle;
	private String companyName;
	
	public JobDTO() {
		
	}
	public JobDTO(int jobId, String jobTitle, String companyName) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.companyName = companyName;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "JobDTO [jobId=" + jobId + ", jobTitle=" + jobTitle + ", companyName=" + companyName + "]";
	}
	
	
}
