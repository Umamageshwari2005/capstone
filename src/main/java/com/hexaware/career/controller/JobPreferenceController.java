package com.hexaware.career.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.dto.JobPreferenceDTO;
import com.hexaware.career.entity.JobPreference;
import com.hexaware.career.service.IJobPreferenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/preferences")
@CrossOrigin(origins = "*")
public class JobPreferenceController {

    private final IJobPreferenceService service;

    public JobPreferenceController(IJobPreferenceService service) {
        this.service = service;
    }

    // Add Job Preference
    @PostMapping
    public ResponseEntity<JobPreference> addPreference(
            @Valid @RequestBody JobPreferenceDTO dto) {

        return new ResponseEntity<>(
                service.addPreference(dto),
                HttpStatus.CREATED);
    }

    // Update Job Preference
    @PutMapping("/{preferenceId}")
    public ResponseEntity<JobPreference> updatePreference(
            @PathVariable int preferenceId,
            @Valid @RequestBody JobPreferenceDTO dto) {

        return ResponseEntity.ok(
                service.updatePreference(preferenceId, dto));
    }

    // Get Preference By Id
    @GetMapping("/{preferenceId}")
    public ResponseEntity<JobPreferenceDTO> getPreferenceById(
            @PathVariable int preferenceId) {

        return ResponseEntity.ok(
                service.getPreferenceById(preferenceId));
    }

    // Get All Preferences Of User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobPreferenceDTO>> getPreferencesByUser(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                service.getPreferencesByUser(userId));
    }

    // Delete Preference
    @DeleteMapping("/{preferenceId}")
    public ResponseEntity<String> deletePreference(
            @PathVariable int preferenceId) {

        service.deletePreference(preferenceId);

        return ResponseEntity.ok("Job Preference Deleted Successfully");
    }

    // Get All Preferences
    @GetMapping
    public ResponseEntity<List<JobPreference>> getAllPreferences() {

        return ResponseEntity.ok(
                service.getAllPreferences());
    }

}