package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.SkillDTO;
import com.hexaware.career.entity.Skill;
import com.hexaware.career.service.ISkillService;

@SpringBootTest
public class SkillServiceTest {

    @Autowired
    private ISkillService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testAddSkill() {

        SkillDTO dto = new SkillDTO();

        dto.setUserId(1);
        dto.setSkillName("Java");

        Skill skill = service.addSkill(dto);

        assertNotNull(skill);
        assertEquals("Java", skill.getSkillName());
    }

    @Test
    void testGetSkillById() {

        SkillDTO dto = service.getSkillById(1);

        assertNotNull(dto);
    }

    @Test
    void testUpdateSkill() {

        SkillDTO dto = new SkillDTO();

        dto.setSkillName("Spring Boot");

        Skill skill = service.updateSkill(1, dto);

        assertNotNull(skill);
        assertEquals("Spring Boot", skill.getSkillName());
    }

    @Test
    void testGetSkillsByUser() {

        List<SkillDTO> skills = service.getSkillsByUser(1);

        assertNotNull(skills);
    }

    @Test
    void testGetAllSkills() {

        List<Skill> skills = service.getAllSkills();

        assertNotNull(skills);
    }

    @Test
    void testDeleteSkill() {

        service.deleteSkill(1);

        assertTrue(true);
    }
}