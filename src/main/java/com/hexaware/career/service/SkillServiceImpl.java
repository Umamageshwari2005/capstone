package com.hexaware.career.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.career.dto.SkillDTO;
import com.hexaware.career.entity.Skill;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.SkillNotFoundException;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.SkillRepository;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SkillServiceImpl implements ISkillService {

    private static final Logger logger =
            LoggerFactory.getLogger(SkillServiceImpl.class);

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillServiceImpl(SkillRepository skillRepository,
                            UserRepository userRepository) {

        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Skill addSkill(SkillDTO dto) {

        logger.info("Adding Skill");

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        Skill skill = new Skill();

        skill.setSkillName(dto.getSkillName());
        skill.setUser(user);

        Skill savedSkill = skillRepository.save(skill);

        logger.info("Skill Added Successfully");

        return savedSkill;
    }

    @Override
    public Skill updateSkill(int skillId, SkillDTO dto) {

        logger.info("Updating Skill");

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new SkillNotFoundException("Skill Not Found"));

        skill.setSkillName(dto.getSkillName());

        Skill updatedSkill = skillRepository.save(skill);

        logger.info("Skill Updated Successfully");

        return updatedSkill;
    }

    @Override
    public SkillDTO getSkillById(int skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new SkillNotFoundException("Skill Not Found"));

        SkillDTO dto = new SkillDTO();

        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());
        dto.setUserId(skill.getUser().getUserId());

        return dto;
    }

    @Override
    public List<SkillDTO> getSkillsByUser(int userId) {

        List<Skill> skills = skillRepository.findByUserUserId(userId);

        List<SkillDTO> list = new ArrayList<>();

        for (Skill skill : skills) {

            SkillDTO dto = new SkillDTO();

            dto.setSkillId(skill.getSkillId());
            dto.setSkillName(skill.getSkillName());
            dto.setUserId(userId);

            list.add(dto);
        }

        return list;
    }

    @Override
    public List<Skill> getAllSkills() {

        return skillRepository.findAll();
    }

    @Override
    public void deleteSkill(int skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new SkillNotFoundException("Skill Not Found"));

        skillRepository.delete(skill);

        logger.info("Skill Deleted Successfully");
    }

}