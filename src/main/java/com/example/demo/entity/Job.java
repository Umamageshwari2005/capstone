package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	private String jobTitle;
	private String companyName;
	@OneToMany(mappedBy="job",
	           cascade=CascadeType.ALL)
	private List<Application> applications;
	@ManyToOne
	@JoinColumn(name="employer_id")
	private User employer;
	public Job() {
		
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
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public User getEmployer() {
		return employer;
	}
	public void setEmployer(User employer) {
		this.employer = employer;
	}
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", companyName=" + companyName + "]";
	}
	
}
