package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;

public interface ISkillService {

    Skill addSkill(SkillDTO dto);

    Skill updateSkill(SkillDTO dto);

    SkillDTO getSkillById(int id);

    void deleteSkill(int id);

    List<Skill> getAllSkills();
}