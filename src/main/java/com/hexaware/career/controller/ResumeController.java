package com.hexaware.career.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.career.dto.ResumeDTO;
import com.hexaware.career.entity.Resume;
import com.hexaware.career.service.IResumeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "*")
public class ResumeController {

    private final IResumeService service;

    public ResumeController(IResumeService service) {
        this.service = service;
    }

    // Upload Resume
    @PostMapping("/upload")
    public ResponseEntity<Resume> uploadResume(
            @RequestParam("resumeName") String resumeName,
            @RequestParam("userId") Integer userId,
            @RequestParam("file") MultipartFile file) throws IOException {

        return new ResponseEntity<>(
                service.uploadResume(resumeName, userId, file),
                HttpStatus.CREATED);
    }

    // Update Resume
    @PutMapping("/{resumeId}")
    public ResponseEntity<Resume> updateResume(
            @PathVariable int resumeId,
            @RequestParam("resumeName") String resumeName,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        return ResponseEntity.ok(
                service.updateResume(resumeId, resumeName, file));
    }

    // Download Resume
    @GetMapping("/download/{resumeId}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadResume(
            @PathVariable int resumeId) throws IOException {

        ResumeDTO dto = service.getResumeById(resumeId);
        Resume resume = service.getAllResumes().stream()
                .filter(r -> r.getResumeId() == resumeId)
                .findFirst()
                .orElseThrow(() -> new com.hexaware.career.exception.ResumeNotFoundException("Resume Not Found"));

        Path path = Paths.get(resume.getFilePath());
        org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resume.getFileName() + "\"")
                .body(resource);
    }

    // Get Resume By Resume Id
    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeDTO> getResumeById(
            @PathVariable int resumeId) {

        return ResponseEntity.ok(
                service.getResumeById(resumeId));
    }

    // Get All Resumes of a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResumeDTO>> getResumesByUser(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                service.getResumesByUser(userId));
    }

    // Delete Resume
    @DeleteMapping("/{resumeId}")
    public ResponseEntity<String> deleteResume(
            @PathVariable int resumeId) {

        service.deleteResume(resumeId);

        return ResponseEntity.ok("Resume Deleted Successfully");
    }

    // Get All Resumes
    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {

        return ResponseEntity.ok(
                service.getAllResumes());
    }

}