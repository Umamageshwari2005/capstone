package com.hexaware.career.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobDTO {

    private int jobId;

    @NotBlank(message = "Job Title is required")
    private String jobTitle;

    @NotBlank(message = "Company Name is required")
    private String companyName;

    @NotBlank(message = "Job Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 0, message = "Salary cannot be negative")
    private double salary;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Job Type is required")
    private String jobType;

    @NotBlank(message = "Required Skills are required")
    private String requiredSkills;

    @NotNull(message = "Employer Id is required")
    private Integer employerId;

    private String status = "Active";

    private String companyEmail;
    private String companyPhone;
    private String companyAddress;
    private String companyWebsite;
    private String companyDescription;
    private java.time.LocalDate postedDate;

    public JobDTO() {

    }

    public JobDTO(int jobId, String jobTitle, String companyName,
                  String description, String location,
                  double salary, String experience,
                  String jobType, String requiredSkills,
                  Integer employerId) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.experience = experience;
        this.jobType = jobType;
        this.requiredSkills = requiredSkills;
        this.employerId = employerId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public java.time.LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(java.time.LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "JobDTO [jobId=" + jobId +
                ", jobTitle=" + jobTitle +
                ", companyName=" + companyName +
                ", description=" + description +
                ", location=" + location +
                ", salary=" + salary +
                ", experience=" + experience +
                ", jobType=" + jobType +
                ", requiredSkills=" + requiredSkills +
                ", employerId=" + employerId +
                ", companyWebsite=" + companyWebsite + "]";
    }
}