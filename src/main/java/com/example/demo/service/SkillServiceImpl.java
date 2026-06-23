package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;
import com.example.demo.exception.SkillNotFoundException;
import com.example.demo.repository.SkillRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SkillServiceImpl implements ISkillService {

    @Autowired
    SkillRepository repo;

    @Override
    public Skill addSkill(SkillDTO dto) {

        Skill skill = new Skill();

        skill.setSkillId(dto.getSkillId());
        skill.setSkillName(dto.getSkillName());

        return repo.save(skill);
    }

    @Override
    public Skill updateSkill(SkillDTO dto) {

        Skill skill = new Skill();

        skill.setSkillId(dto.getSkillId());
        skill.setSkillName(dto.getSkillName());

        return repo.save(skill);
    }

    @Override
    public SkillDTO getSkillById(int id) {

    	Skill skill = repo.findById(id)
    	        .orElseThrow(() ->
    	        new SkillNotFoundException(
    	                "Skill Not Found With Id : " + id));

        SkillDTO dto = new SkillDTO();

        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());

        return dto;
    }

    @Override
    public void deleteSkill(int id) {
    	
    	 Skill skill = repo.findById(id)
    	            .orElseThrow(() ->
    	            new SkillNotFoundException(
    	                    "Skill Not Found With Id : " + id));

        repo.deleteById(id);
    }

    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }
}