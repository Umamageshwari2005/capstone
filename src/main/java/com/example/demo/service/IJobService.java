package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Job;

public interface IJobService {

    Job addJob(JobDTO dto);

    Job updateJob(JobDTO dto);

    JobDTO getJobById(int id);

    void deleteJob(int id);

    List<Job> getAllJobs();
}