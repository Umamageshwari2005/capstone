package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.JobPreferenceDTO;
import com.example.demo.entity.JobPreference;

@SpringBootTest
public class JobPreferenceServiceTest {

    @Autowired
    private IJobPreferenceService service;

    @Test
    void testAddPreference() {

        JobPreferenceDTO dto = new JobPreferenceDTO();

        dto.setPreferenceId(301);
        dto.setPreferredRole("Java Developer");
        dto.setLocation("Chennai");

        JobPreference preference = service.addPreference(dto);

        assertNotNull(preference);
        assertEquals("Java Developer",
                     preference.getPreferredRole());
    }

    @Test
    void testGetPreferenceById() {

        JobPreferenceDTO dto =
                service.getPreferenceById(301);

        assertNotNull(dto);

        assertEquals(301,
                     dto.getPreferenceId());
    }

    @Test
    void testUpdatePreference() {

        JobPreferenceDTO dto =
                new JobPreferenceDTO();

        dto.setPreferenceId(301);
        dto.setPreferredRole("Spring Boot Developer");
        dto.setLocation("Bangalore");

        JobPreference preference =
                service.updatePreference(dto);

        assertNotNull(preference);

        assertEquals(
                "Spring Boot Developer",
                preference.getPreferredRole());
    }

    @Test
    void testGetAllPreferences() {

        List<JobPreference> preferences =
                service.getAllPreferences();

        assertNotNull(preferences);

        assertTrue(preferences.size() >= 0);
    }

    @Test
    void testDeletePreference() {

        service.deletePreference(301);

        assertTrue(true);
    }
}