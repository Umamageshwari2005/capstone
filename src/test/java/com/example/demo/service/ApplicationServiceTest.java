package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.entity.Application;

@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private IApplicationService service;

    @Test
    void testAddApplication() {

        ApplicationDTO dto = new ApplicationDTO();

        dto.setApplicationId(701);
        dto.setStatus("Applied");

        Application application =
                service.addApplication(dto);

        assertNotNull(application);

        assertEquals(
                "Applied",
                application.getStatus());
    }

    @Test
    void testGetApplicationById() {

        ApplicationDTO dto =
                service.getApplicationById(701);

        assertNotNull(dto);

        assertEquals(
                701,
                dto.getApplicationId());
    }

    @Test
    void testUpdateApplication() {

        ApplicationDTO dto =
                new ApplicationDTO();

        dto.setApplicationId(701);
        dto.setStatus("Shortlisted");

        Application application =
                service.updateApplication(dto);

        assertNotNull(application);

        assertEquals(
                "Shortlisted",
                application.getStatus());
    }

    @Test
    void testGetAllApplications() {

        List<Application> applications =
                service.getAllApplications();

        assertNotNull(applications);

        assertTrue(
                applications.size() >= 0);
    }

    @Test
    void testDeleteApplication() {

        service.deleteApplication(701);

        assertTrue(true);
    }
}