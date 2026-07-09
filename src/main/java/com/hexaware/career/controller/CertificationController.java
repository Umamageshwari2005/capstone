package com.hexaware.career.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.career.entity.Certification;
import com.hexaware.career.entity.User;
import com.hexaware.career.repository.CertificationRepository;
import com.hexaware.career.repository.UserRepository;

@RestController
@RequestMapping("/api/certifications")
@CrossOrigin(origins = "*")
public class CertificationController {

    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final String UPLOAD_DIR = "uploads/";

    public CertificationController(CertificationRepository certificationRepository, UserRepository userRepository) {
        this.certificationRepository = certificationRepository;
        this.userRepository = userRepository;
    }

    // Upload Certification PDF
    @PostMapping("/upload")
    public ResponseEntity<Certification> uploadCertification(
            @RequestParam("certificationName") String name,
            @RequestParam("userId") Integer userId,
            @RequestParam("file") MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_cert_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.copy(file.getInputStream(), filePath);

        Certification cert = new Certification();
        cert.setCertificationName(name);
        cert.setFilePath(filePath.toString());
        cert.setFileUrl("/api/certifications/download/" + fileName);
        cert.setUser(user);

        Certification saved = certificationRepository.save(cert);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // List Certifications of User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Certification>> getCertificationsByUser(@PathVariable int userId) {
        return ResponseEntity.ok(certificationRepository.findByUser_UserId(userId));
    }

    // Download/View Certification PDF
    @GetMapping("/download/{fileName}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadCertification(@PathVariable String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_DIR, fileName);
        org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }

    // Delete Certification
    @DeleteMapping("/{certificationId}")
    public ResponseEntity<String> deleteCertification(@PathVariable int certificationId) {
        Certification cert = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new RuntimeException("Certification Not Found"));

        File file = new File(cert.getFilePath());
        if (file.exists()) {
            file.delete();
        }

        certificationRepository.delete(cert);
        return ResponseEntity.ok("Certification Deleted Successfully");
    }
}
