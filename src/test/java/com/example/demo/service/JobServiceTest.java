package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Job;

@SpringBootTest
public class JobServiceTest {

    @Autowired
    private IJobService service;

    @Test
    void testAddJob() {

        JobDTO dto = new JobDTO();

        dto.setJobId(201);
        dto.setJobTitle("Java Developer");
        dto.setCompanyName("Hexaware");

        Job job = service.addJob(dto);

        assertNotNull(job);
        assertEquals("Java Developer", job.getJobTitle());
    }

    @Test
    void testGetJobById() {

        JobDTO job = service.getJobById(201);

        assertNotNull(job);
        assertEquals(201, job.getJobId());
    }

    @Test
    void testUpdateJob() {

        JobDTO dto = new JobDTO();

        dto.setJobId(201);
        dto.setJobTitle("Spring Boot Developer");
        dto.setCompanyName("Hexaware");

        Job job = service.updateJob(dto);

        assertNotNull(job);
        assertEquals("Spring Boot Developer", job.getJobTitle());
    }

    @Test
    void testGetAllJobs() {

        List<Job> jobs = service.getAllJobs();

        assertNotNull(jobs);
        assertTrue(jobs.size() >= 0);
    }

    @Test
    void testDeleteJob() {

        service.deleteJob(201);

        assertTrue(true);
    }
}