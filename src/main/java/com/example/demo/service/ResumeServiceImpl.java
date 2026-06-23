package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResumeDTO;
import com.example.demo.entity.Resume;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.repository.ResumeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResumeServiceImpl implements IResumeService {

    @Autowired
    ResumeRepository repo;

    @Override
    public Resume addResume(ResumeDTO dto) {

        Resume resume = new Resume();

        resume.setResumeId(dto.getResumeId());
        resume.setResumeName(dto.getResumeName());

        return repo.save(resume);
    }

    @Override
    public Resume updateResume(ResumeDTO dto) {

        Resume resume = new Resume();

        resume.setResumeId(dto.getResumeId());
        resume.setResumeName(dto.getResumeName());

        return repo.save(resume);
    }

    @Override
    public ResumeDTO getResumeById(int id) {

    	Resume resume = repo.findById(id)
                .orElseThrow(() ->
                new ResumeNotFoundException(
                        "Resume Not Found With Id : " + id));

        ResumeDTO dto = new ResumeDTO();

        dto.setResumeId(resume.getResumeId());
        dto.setResumeName(resume.getResumeName());

        return dto;
    }

    @Override
    public void deleteResume(int id) {
    	 Resume resume = repo.findById(id)
    	            .orElseThrow(() ->
    	            new ResumeNotFoundException(
    	                    "Resume Not Found With Id : " + id));
        repo.deleteById(id);
    }

    @Override
    public List<Resume> getAllResumes() {
        return repo.findAll();
    }
}