package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Job;
import com.example.demo.service.IJobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    IJobService service;

    @PostMapping("/add")
    public Job addJob(@RequestBody JobDTO dto) {

        return service.addJob(dto);
    }

    @PutMapping("/update")
    public Job updateJob(@RequestBody JobDTO dto) {

        return service.updateJob(dto);
    }

    @GetMapping("/getbyid/{id}")
    public JobDTO getJobById(@PathVariable int id) {

        return service.getJobById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable int id) {

        service.deleteJob(id);

        return "Job Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<Job> getAllJobs() {

        return service.getAllJobs();
    }
}