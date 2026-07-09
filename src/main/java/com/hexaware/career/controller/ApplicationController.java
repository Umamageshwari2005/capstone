package com.hexaware.career.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.dto.ApplicationDTO;
import com.hexaware.career.entity.Application;
import com.hexaware.career.service.IApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final IApplicationService service;

    public ApplicationController(IApplicationService service) {
        this.service = service;
    }

    // Job Seeker applies for a job
    @PostMapping
    public ResponseEntity<Application> applyJob(
            @Valid @RequestBody ApplicationDTO dto) {

        return new ResponseEntity<>(
                service.applyJob(dto),
                HttpStatus.CREATED);
    }
    // Update Application Status
    @PutMapping("/{applicationId}")
    public ResponseEntity<Application> updateApplication(
            @PathVariable int applicationId,
            @Valid @RequestBody ApplicationDTO dto) {

        return ResponseEntity.ok(
                service.updateApplication(applicationId, dto));
    }

    // Get Application By Id
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> getApplicationById(
            @PathVariable int applicationId) {

        return ResponseEntity.ok(
                service.getApplicationById(applicationId));
    }

    // Get All Applications of a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByUser(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                service.getApplicationsByUser(userId));
    }

    // Employer views candidates who applied for a job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByJob(
            @PathVariable int jobId) {

        return ResponseEntity.ok(
                service.getApplicationsByJob(jobId));
    }

    // Employer views all applications for their posted jobs
    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<com.hexaware.career.dto.ApplicantDetailsDTO>> getApplicantsByEmployer(
            @PathVariable int employerId) {

        return ResponseEntity.ok(
                service.getApplicantsByEmployer(employerId));
    }

    // Delete Application
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable int applicationId) {

        service.deleteApplication(applicationId);

        return ResponseEntity.ok("Application Deleted Successfully");
    }

    // Get All Applications
    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {

        return ResponseEntity.ok(
                service.getAllApplications());
    }

    @GetMapping("/details/{applicationId}")
    public ResponseEntity<com.hexaware.career.dto.ApplicantDetailsDTO> getApplicantDetailsByApplicationId(
            @PathVariable int applicationId) {

        return ResponseEntity.ok(
                service.getApplicantDetailsByApplicationId(applicationId));
    }

    @GetMapping("/employer/{employerId}/search")
    public ResponseEntity<List<com.hexaware.career.dto.ApplicantDetailsDTO>> searchApplicantsByApplicantId(
            @PathVariable int employerId,
            @RequestParam int applicantId) {

        return ResponseEntity.ok(
                service.searchApplicantsByApplicantId(employerId, applicantId));
    }

}