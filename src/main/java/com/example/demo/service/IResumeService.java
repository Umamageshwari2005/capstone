package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ResumeDTO;
import com.example.demo.entity.Resume;


public interface IResumeService {

    Resume addResume(ResumeDTO dto);

    Resume updateResume(ResumeDTO dto);

    ResumeDTO getResumeById(int id);

    void deleteResume(int id);

    List<Resume> getAllResumes();
}