package com.example.demo.dto;

public class ResumeDTO {
	private int resumeId;
	private  String resumeName;
	
	public ResumeDTO() {
    }

	public ResumeDTO(int resumeId, String resumeName) {
		super();
		this.resumeId = resumeId;
		this.resumeName = resumeName;
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
	@Override
	public String toString() {
		return "ResumeDTO [resumeId=" + resumeId + ", resumeName=" + resumeName + "]";
	}
	
	
}
