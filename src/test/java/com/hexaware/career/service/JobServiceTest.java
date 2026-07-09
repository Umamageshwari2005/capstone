package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.JobDTO;
import com.hexaware.career.entity.Job;
import com.hexaware.career.service.IJobService;

@SpringBootTest
public class JobServiceTest {

    @Autowired
    private IJobService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testAddJob() {

        JobDTO dto = new JobDTO();

        dto.setEmployerId(1);
        dto.setJobTitle("Java Developer");
        dto.setCompanyName("Hexaware");
        dto.setDescription("Java Developer");
        dto.setLocation("Chennai");
        dto.setSalary(600000);
        dto.setExperience("2 Years");
        dto.setJobType("Full Time");
        dto.setRequiredSkills("Java, Spring Boot");

        Job job = service.addJob(dto);

        assertNotNull(job);
        assertEquals("Java Developer", job.getJobTitle());
    }

    @Test
    void testGetJobById() {

        JobDTO job = service.getJobById(1);

        assertNotNull(job);
    }

    @Test
    void testUpdateJob() {

        JobDTO dto = new JobDTO();

        dto.setJobTitle("Spring Boot Developer");
        dto.setCompanyName("Hexaware");
        dto.setDescription("Backend Developer");
        dto.setLocation("Bangalore");
        dto.setSalary(800000);
        dto.setExperience("3 Years");
        dto.setJobType("Full Time");
        dto.setRequiredSkills("Spring Boot");

        Job job = service.updateJob(1, dto);

        assertNotNull(job);
        assertEquals("Spring Boot Developer", job.getJobTitle());
    }

    @Test
    void testGetAllJobs() {

        List<com.hexaware.career.dto.JobDTO> jobs = service.getAllJobs();

        assertNotNull(jobs);
    }

    @Test
    void testDeleteJob() {

        service.deleteJob(1);

        assertTrue(true);
    }

}