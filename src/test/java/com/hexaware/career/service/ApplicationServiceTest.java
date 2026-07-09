package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.ApplicationDTO;
import com.hexaware.career.entity.Application;
import com.hexaware.career.service.IApplicationService;

@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private IApplicationService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testApplyJob() {

        ApplicationDTO dto = new ApplicationDTO();

        dto.setUserId(1);
        dto.setJobId(1);
        dto.setStatus("Applied");

        Application application = service.applyJob(dto);

        assertNotNull(application);
        assertEquals("Applied", application.getStatus());
    }

    @Test
    void testGetApplicationById() {

        ApplicationDTO dto = service.getApplicationById(1);

        assertNotNull(dto);
    }

    @Test
    void testUpdateApplication() {

        ApplicationDTO dto = new ApplicationDTO();

        dto.setStatus("Shortlisted");

        Application application =
                service.updateApplication(1, dto);

        assertNotNull(application);
        assertEquals("Shortlisted", application.getStatus());
    }

    @Test
    void testGetApplicationsByUser() {

        List<ApplicationDTO> applications =
                service.getApplicationsByUser(1);

        assertNotNull(applications);
    }

    @Test
    void testGetApplicationsByJob() {

        List<ApplicationDTO> applications =
                service.getApplicationsByJob(1);

        assertNotNull(applications);
    }

    @Test
    void testGetAllApplications() {

        List<Application> applications =
                service.getAllApplications();

        assertNotNull(applications);
    }

    @Test
    void testDeleteApplication() {

        service.deleteApplication(1);

        assertTrue(true);
    }
}