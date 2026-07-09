package com.hexaware.career.service;

import java.util.List;

import com.hexaware.career.dto.SkillDTO;
import com.hexaware.career.entity.Skill;

public interface ISkillService {

    Skill addSkill(SkillDTO dto);

    Skill updateSkill(int skillId, SkillDTO dto);

    SkillDTO getSkillById(int skillId);

    List<SkillDTO> getSkillsByUser(int userId);

    List<Skill> getAllSkills();

    void deleteSkill(int skillId);

}