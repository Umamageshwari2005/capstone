package com.hexaware.career.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.career.dto.ResumeDTO;
import com.hexaware.career.entity.Resume;

public interface IResumeService {

    Resume uploadResume(String resumeName, Integer userId, MultipartFile file) throws java.io.IOException;

    Resume updateResume(int resumeId, String resumeName, MultipartFile file) throws java.io.IOException;

    ResumeDTO getResumeById(int resumeId);

    List<ResumeDTO> getResumesByUser(int userId);

    List<Resume> getAllResumes();

    void deleteResume(int resumeId);

    Resume addResume(ResumeDTO dto);

    Resume updateResume(int resumeId, ResumeDTO dto);

}