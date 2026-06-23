package com.example.demo.dto;

public class SkillDTO {
	private int skillId;
	private String skillName;
	
	public SkillDTO() {
		
	}
	public SkillDTO(int skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	@Override
	public String toString() {
		return "SkillDTO [skillId=" + skillId + ", skillName=" + skillName + "]";
	}
	
	
}
