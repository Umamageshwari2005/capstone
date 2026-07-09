package com.hexaware.career.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SkillDTO {

    private int skillId;

    @NotBlank(message = "Skill Name is required")
    private String skillName;

    @NotNull(message = "User Id is required")
    private Integer userId;

    public SkillDTO() {

    }

    public SkillDTO(int skillId, String skillName, Integer userId) {
        super();
        this.skillId = skillId;
        this.skillName = skillName;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SkillDTO [skillId=" + skillId
                + ", skillName=" + skillName
                + ", userId=" + userId + "]";
    }

}