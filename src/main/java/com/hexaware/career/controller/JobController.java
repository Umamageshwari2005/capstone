package com.hexaware.career.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.dto.JobDTO;
import com.hexaware.career.entity.Job;
import com.hexaware.career.service.IJobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final IJobService service;

    public JobController(IJobService service) {
        this.service = service;
    }

    // Employer Post Job
    @PostMapping
    public ResponseEntity<Job> addJob(
            @Valid @RequestBody JobDTO dto) {

        return new ResponseEntity<>(
                service.addJob(dto),
                HttpStatus.CREATED);
    }

    // Employer Update Job
    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(
            @PathVariable int jobId,
            @Valid @RequestBody JobDTO dto) {

        return ResponseEntity.ok(
                service.updateJob(jobId, dto));
    }

    // View Job By Id
    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJobById(
            @PathVariable int jobId) {

        return ResponseEntity.ok(
                service.getJobById(jobId));
    }

    // View All Jobs
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {

        return ResponseEntity.ok(
                service.getAllJobs());
    }

    // Employer View Posted Jobs
    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobDTO>> getJobsByEmployer(
            @PathVariable int employerId) {

        return ResponseEntity.ok(
                service.getJobsByEmployer(employerId));
    }

    // Search Jobs By Title
    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<JobDTO>> searchByTitle(
            @PathVariable String title) {

        return ResponseEntity.ok(
                service.searchByTitle(title));
    }
    // Search Jobs By Location
    @GetMapping("/search/location/{location}")
    public ResponseEntity<List<JobDTO>> searchByLocation(
            @PathVariable String location) {

        return ResponseEntity.ok(
                service.searchByLocation(location));
    }

    // Delete Job
    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(
            @PathVariable int jobId) {

        service.deleteJob(jobId);

        return ResponseEntity.ok("Job Deleted Successfully");
    }

}