package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Job;
import com.example.demo.exception.JobNotFoundException;
import com.example.demo.repository.JobRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobServiceImpl implements IJobService {

    @Autowired
    JobRepository repo;

    @Override
    public Job addJob(JobDTO dto) {

        Job job = new Job();

        job.setJobId(dto.getJobId());
        job.setJobTitle(dto.getJobTitle());
        job.setCompanyName(dto.getCompanyName());

        return repo.save(job);
    }

    @Override
    public Job updateJob(JobDTO dto) {

        Job job = new Job();

        job.setJobId(dto.getJobId());
        job.setJobTitle(dto.getJobTitle());
        job.setCompanyName(dto.getCompanyName());

        return repo.save(job);
    }

    @Override
    public JobDTO getJobById(int id) {

    	repo.findById(id)
    	.orElseThrow(() ->
    	new JobNotFoundException(
    	"Job Not Found With Id : " + id));

        JobDTO dto = new JobDTO();

        dto.setJobId(dto.getJobId());
        dto.setJobTitle(dto.getJobTitle());
        dto.setCompanyName(dto.getCompanyName());

        return dto;
    }

    @Override
    public void deleteJob(int id) {
    	
    	
    	

    	    Job job = repo.findById(id)
    	            .orElseThrow(() ->
    	            new JobNotFoundException(
    	                    "Job Not Found With Id : " + id));

    	   
        repo.deleteById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return repo.findAll();
    }
}