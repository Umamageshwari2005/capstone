package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ResumeDTO;
import com.example.demo.entity.Resume;

@SpringBootTest
public class ResumeServiceTest {

    @Autowired
    private IResumeService service;

    @Test
    void testAddResume() {

        ResumeDTO dto = new ResumeDTO();

        dto.setResumeId(501);
        dto.setResumeName("Java_Resume.pdf");

        Resume resume = service.addResume(dto);

        assertNotNull(resume);

        assertEquals(
                "Java_Resume.pdf",
                resume.getResumeName());
    }

    @Test
    void testGetResumeById() {

        ResumeDTO dto =
                service.getResumeById(501);

        assertNotNull(dto);

        assertEquals(
                501,
                dto.getResumeId());
    }

    @Test
    void testUpdateResume() {

        ResumeDTO dto =
                new ResumeDTO();

        dto.setResumeId(501);
        dto.setResumeName("Updated_Resume.pdf");

        Resume resume =
                service.updateResume(dto);

        assertNotNull(resume);

        assertEquals(
                "Updated_Resume.pdf",
                resume.getResumeName());
    }

    @Test
    void testGetAllResumes() {

        List<Resume> resumes =
                service.getAllResumes();

        assertNotNull(resumes);

        assertTrue(
                resumes.size() >= 0);
    }

    @Test
    void testDeleteResume() {

        service.deleteResume(501);

        assertTrue(true);
    }
}