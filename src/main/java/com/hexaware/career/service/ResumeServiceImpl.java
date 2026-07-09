package com.hexaware.career.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.career.dto.ResumeDTO;
import com.hexaware.career.entity.Resume;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.ResumeNotFoundException;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.ResumeRepository;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResumeServiceImpl implements IResumeService {

    private static final Logger logger =
            LoggerFactory.getLogger(ResumeServiceImpl.class);

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    private final String UPLOAD_DIR = "uploads/";

    public ResumeServiceImpl(ResumeRepository resumeRepository,
                             UserRepository userRepository) {

        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;

    }

    @Override
    public Resume uploadResume(String resumeName,
                               Integer userId,
                               MultipartFile file) throws IOException {

        logger.info("Uploading Resume");

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        File folder = new File(UPLOAD_DIR);

        if (!folder.exists()) {

            folder.mkdirs();

        }

        String fileName = System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename();

        Path filePath =
                Paths.get(UPLOAD_DIR, fileName);

        Files.copy(file.getInputStream(), filePath);

        Resume resume = new Resume();

        resume.setResumeName(resumeName);

        resume.setFileName(fileName);

        resume.setFilePath(filePath.toString());

        resume.setUploadedDate(LocalDate.now());

        resume.setUser(user);

        Resume savedResume =
                resumeRepository.save(resume);

        logger.info("Resume Uploaded Successfully");

        return savedResume;

    }

    @Override
    public Resume updateResume(int resumeId,
                               String resumeName,
                               MultipartFile file) throws IOException {

        Resume resume =
                resumeRepository.findById(resumeId)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume Not Found"));

        if (file != null && !file.isEmpty()) {

            String fileName =
                    System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename();

            Path filePath =
                    Paths.get(UPLOAD_DIR, fileName);

            Files.copy(file.getInputStream(), filePath);

            resume.setFileName(fileName);

            resume.setFilePath(filePath.toString());

        }

        resume.setResumeName(resumeName);

        return resumeRepository.save(resume);

    }

    @Override
    public ResumeDTO getResumeById(int resumeId) {

        Resume resume =
                resumeRepository.findById(resumeId)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume Not Found"));

        ResumeDTO dto = new ResumeDTO();

        dto.setResumeId(resume.getResumeId());

        dto.setResumeName(resume.getResumeName());

        dto.setUserId(resume.getUser().getUserId());

        dto.setResumeUrl("/api/resumes/download/" + resume.getResumeId());

        return dto;

    }

    @Override
    public List<ResumeDTO> getResumesByUser(int userId) {

        List<Resume> resumes =
                resumeRepository.findByUserUserId(userId);

        List<ResumeDTO> list =
                new ArrayList<>();

        for (Resume resume : resumes) {

            ResumeDTO dto =
                    new ResumeDTO();

            dto.setResumeId(resume.getResumeId());

            dto.setResumeName(resume.getResumeName());

            dto.setUserId(userId);

            dto.setResumeUrl("/api/resumes/download/" + resume.getResumeId());

            list.add(dto);

        }

        return list;

    }

    @Override
    public List<Resume> getAllResumes() {

        return resumeRepository.findAll();

    }

    @Override
    public void deleteResume(int resumeId) {

        Resume resume =
                resumeRepository.findById(resumeId)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume Not Found"));

        File file =
                new File(resume.getFilePath());

        if (file.exists()) {

            file.delete();

        }

        resumeRepository.delete(resume);

        logger.info("Resume Deleted Successfully");

    }

    @Override
    public Resume addResume(com.hexaware.career.dto.ResumeDTO dto) {
        org.springframework.web.multipart.MultipartFile mockFile = new org.springframework.web.multipart.MultipartFile() {
            @Override
            public String getName() { return "file"; }
            @Override
            public String getOriginalFilename() { return dto.getResumeName(); }
            @Override
            public String getContentType() { return "application/pdf"; }
            @Override
            public boolean isEmpty() { return false; }
            @Override
            public long getSize() { return 0; }
            @Override
            public byte[] getBytes() throws java.io.IOException { return new byte[0]; }
            @Override
            public java.io.InputStream getInputStream() throws java.io.IOException { return new java.io.ByteArrayInputStream(new byte[0]); }
            @Override
            public void transferTo(java.io.File dest) throws java.io.IOException, IllegalStateException {}
        };
        try {
            return uploadResume(dto.getResumeName(), dto.getUserId(), mockFile);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resume updateResume(int resumeId, com.hexaware.career.dto.ResumeDTO dto) {
        try {
            return updateResume(resumeId, dto.getResumeName(), null);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

}