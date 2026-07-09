package com.hexaware.career.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

public class ApplicationDTO {

    private int applicationId;

    @NotBlank(message = "Application Status is required")
    private String status;

    private Integer userId;

    private Integer jobId;

    private String jobTitle;

    private String companyName;

    private Integer companyId;

    private String jobDescription;

    private LocalDate appliedDate;

    private String employerUpdates;

    public ApplicationDTO() {

    }

    public ApplicationDTO(int applicationId, String status,
                          Integer userId, Integer jobId) {
        super();
        this.applicationId = applicationId;
        this.status = status;
        this.userId = userId;
        this.jobId = jobId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getEmployerUpdates() {
        return employerUpdates;
    }

    public void setEmployerUpdates(String employerUpdates) {
        this.employerUpdates = employerUpdates;
    }

    @Override
    public String toString() {
        return "ApplicationDTO [applicationId=" + applicationId
                + ", status=" + status
                + ", userId=" + userId
                + ", jobId=" + jobId
                + ", jobTitle=" + jobTitle
                + ", companyName=" + companyName + "]";
    }

}