package com.hexaware.career.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resumeId;

    @Column(nullable = false)
    private String resumeName;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    private LocalDate uploadedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Resume() {

    }

    public Resume(int resumeId,
                  String resumeName,
                  String fileName,
                  String filePath,
                  LocalDate uploadedDate,
                  User user) {

        this.resumeId = resumeId;
        this.resumeName = resumeName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedDate = uploadedDate;
        this.user = user;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDate getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {

        return "Resume [resumeId=" + resumeId
                + ", resumeName=" + resumeName
                + ", fileName=" + fileName
                + ", filePath=" + filePath
                + ", uploadedDate=" + uploadedDate + "]";

    }

}