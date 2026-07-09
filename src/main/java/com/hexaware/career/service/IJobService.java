package com.hexaware.career.service;

import java.util.List;

import com.hexaware.career.dto.JobDTO;
import com.hexaware.career.entity.Job;

public interface IJobService {

    Job addJob(JobDTO dto);

    Job updateJob(int jobId, JobDTO dto);

    JobDTO getJobById(int jobId);

    List<JobDTO> getJobsByEmployer(int employerId);

    List<JobDTO> getAllJobs();

    List<JobDTO> searchByLocation(String location);

    List<JobDTO> searchByTitle(String title);

    void deleteJob(int jobId);

}