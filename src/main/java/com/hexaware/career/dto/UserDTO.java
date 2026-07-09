package com.hexaware.career.dto;

import java.time.LocalDate;

import com.hexaware.career.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class UserDTO {

    private int userId;

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    private String password;

    private String phone;

    private String gender;

    private LocalDate dob;

    private String address;

    private String degree;

    private String college;

    @PositiveOrZero(message = "CGPA cannot be negative")
    private double cgpa;

    private int passedOutYear;

    private String experience;

    private String currentCompany;

    private String projects;

    private String certifications;

    private String linkedin;

    private String github;

    private String website;

    private String description;

    private Role role;

    private java.util.List<ResumeDTO> resumes;
    private java.util.List<SkillDTO> skills;

    public java.util.List<ResumeDTO> getResumes() {
        return resumes;
    }

    public void setResumes(java.util.List<ResumeDTO> resumes) {
        this.resumes = resumes;
    }

    public java.util.List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(java.util.List<SkillDTO> skills) {
        this.skills = skills;
    }

    public UserDTO() {

    }

    public UserDTO(int userId, String fullName, String email, String password,
            String phone, String gender, LocalDate dob, String address,
            String degree, String college, double cgpa,
            int passedOutYear, String experience,
            String currentCompany, String projects,
            String certifications, String linkedin,
            String github, Role role) {

        super();
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.degree = degree;
        this.college = college;
        this.cgpa = cgpa;
        this.passedOutYear = passedOutYear;
        this.experience = experience;
        this.currentCompany = currentCompany;
        this.projects = projects;
        this.certifications = certifications;
        this.linkedin = linkedin;
        this.github = github;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getPassedOutYear() {
        return passedOutYear;
    }

    public void setPassedOutYear(int passedOutYear) {
        this.passedOutYear = passedOutYear;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}