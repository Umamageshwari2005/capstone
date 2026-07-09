package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.ResumeDTO;
import com.hexaware.career.entity.Resume;
import com.hexaware.career.service.IResumeService;

@SpringBootTest
public class ResumeServiceTest {

    @Autowired
    private IResumeService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testAddResume() {

        ResumeDTO dto = new ResumeDTO();

        dto.setUserId(1);
        dto.setResumeName("Java_Resume.pdf");
        dto.setResumeUrl("C:/Resume/Java_Resume.pdf");

        Resume resume = service.addResume(dto);

        assertNotNull(resume);
        assertEquals("Java_Resume.pdf", resume.getResumeName());
    }

    @Test
    void testGetResumeById() {

        ResumeDTO dto = service.getResumeById(1);

        assertNotNull(dto);
    }

    @Test
    void testUpdateResume() {

        ResumeDTO dto = new ResumeDTO();

        dto.setResumeName("Updated_Resume.pdf");
        dto.setResumeUrl("C:/Resume/Updated_Resume.pdf");

        Resume resume = service.updateResume(1, dto);

        assertNotNull(resume);
        assertEquals("Updated_Resume.pdf", resume.getResumeName());
    }

    @Test
    void testGetResumesByUser() {

        List<ResumeDTO> resumes =
                service.getResumesByUser(1);

        assertNotNull(resumes);
    }

    @Test
    void testGetAllResumes() {

        List<Resume> resumes = service.getAllResumes();

        assertNotNull(resumes);
    }

    @Test
    void testDeleteResume() {

        service.deleteResume(1);

        assertTrue(true);
    }

}