package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ResumeDTO;
import com.example.demo.entity.Resume;
import com.example.demo.service.IResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    IResumeService service;

    @PostMapping("/add")
    public Resume addResume(@RequestBody ResumeDTO dto) {

        return service.addResume(dto);
    }

    @PutMapping("/update")
    public Resume updateResume(@RequestBody ResumeDTO dto) {

        return service.updateResume(dto);
    }

    @GetMapping("/getbyid/{id}")
    public ResumeDTO getResumeById(@PathVariable int id) {

        return service.getResumeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteResume(@PathVariable int id) {

        service.deleteResume(id);

        return "Resume Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<Resume> getAllResumes() {

        return service.getAllResumes();
    }
}