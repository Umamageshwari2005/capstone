package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.JobPreferenceDTO;
import com.hexaware.career.entity.JobPreference;
import com.hexaware.career.service.IJobPreferenceService;

@SpringBootTest
public class JobPreferenceServiceTest {

    @Autowired
    private IJobPreferenceService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testAddPreference() {

        JobPreferenceDTO dto = new JobPreferenceDTO();

        dto.setUserId(1);
        dto.setPreferredRole("Java Developer");
        dto.setPreferredLocation("Chennai");
        dto.setExpectedSalary(600000);

        JobPreference preference = service.addPreference(dto);

        assertNotNull(preference);
        assertEquals("Java Developer", preference.getPreferredRole());
    }

    @Test
    void testGetPreferenceById() {

        JobPreferenceDTO dto = service.getPreferenceById(1);

        assertNotNull(dto);
    }

    @Test
    void testUpdatePreference() {

        JobPreferenceDTO dto = new JobPreferenceDTO();

        dto.setPreferredRole("Spring Boot Developer");
        dto.setPreferredLocation("Bangalore");
        dto.setExpectedSalary(800000);

        JobPreference preference = service.updatePreference(1, dto);

        assertNotNull(preference);
        assertEquals("Spring Boot Developer",
                     preference.getPreferredRole());
    }

    @Test
    void testGetPreferencesByUser() {

        List<JobPreferenceDTO> preferences =
                service.getPreferencesByUser(1);

        assertNotNull(preferences);
    }

    @Test
    void testGetAllPreferences() {

        List<JobPreference> preferences =
                service.getAllPreferences();

        assertNotNull(preferences);
    }

    @Test
    void testDeletePreference() {

        service.deletePreference(1);

        assertTrue(true);
    }
}