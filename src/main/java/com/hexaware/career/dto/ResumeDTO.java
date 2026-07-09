package com.hexaware.career.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResumeDTO {

    private int resumeId;

    @NotBlank(message = "Resume Name is required")
    private String resumeName;

    @NotBlank(message = "Resume URL is required")
    private String resumeUrl;

    @NotNull(message = "User Id is required")
    private Integer userId;

    public ResumeDTO() {

    }

    public ResumeDTO(int resumeId, String resumeName, String resumeUrl, Integer userId) {
        super();
        this.resumeId = resumeId;
        this.resumeName = resumeName;
        this.resumeUrl = resumeUrl;
        this.userId = userId;
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

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ResumeDTO [resumeId=" + resumeId
                + ", resumeName=" + resumeName
                + ", resumeUrl=" + resumeUrl
                + ", userId=" + userId + "]";
    }

}