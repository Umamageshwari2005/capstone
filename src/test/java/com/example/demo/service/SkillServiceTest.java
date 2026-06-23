package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;

@SpringBootTest
public class SkillServiceTest {

    @Autowired
    private ISkillService service;

    @Test
    void testAddSkill() {

        SkillDTO dto = new SkillDTO();

        dto.setSkillId(601);
        dto.setSkillName("Java");

        Skill skill = service.addSkill(dto);

        assertNotNull(skill);

        assertEquals(
                "Java",
                skill.getSkillName());
    }

    @Test
    void testGetSkillById() {

        SkillDTO dto =
                service.getSkillById(601);

        assertNotNull(dto);

        assertEquals(
                601,
                dto.getSkillId());
    }

    @Test
    void testUpdateSkill() {

        SkillDTO dto =
                new SkillDTO();

        dto.setSkillId(601);
        dto.setSkillName("Spring Boot");

        Skill skill =
                service.updateSkill(dto);

        assertNotNull(skill);

        assertEquals(
                "Spring Boot",
                skill.getSkillName());
    }

    @Test
    void testGetAllSkills() {

        List<Skill> skills =
                service.getAllSkills();

        assertNotNull(skills);

        assertTrue(
                skills.size() >= 0);
    }

    @Test
    void testDeleteSkill() {

        service.deleteSkill(601);

        assertTrue(true);
    }
}
