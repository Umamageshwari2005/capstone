package com.hexaware.career.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.career.dto.JobDTO;
import com.hexaware.career.entity.Job;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.JobNotFoundException;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.JobRepository;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobServiceImpl implements IJobService {

    private static final Logger logger =
            LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository,
                          UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Job addJob(JobDTO dto) {

        logger.info("Posting New Job");

        User employer = userRepository.findById(dto.getEmployerId())
                .orElseThrow(() ->
                        new UserNotFoundException("Employer Not Found"));

        Job job = new Job();

        job.setJobTitle(dto.getJobTitle());
        job.setCompanyName(dto.getCompanyName());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setSalary(dto.getSalary());
        job.setExperience(dto.getExperience());
        job.setJobType(dto.getJobType());
        job.setRequiredSkills(dto.getRequiredSkills());
        job.setStatus(dto.getStatus() != null ? dto.getStatus() : "Active");
        job.setCompanyEmail(dto.getCompanyEmail());
        job.setCompanyPhone(dto.getCompanyPhone());
        job.setCompanyAddress(dto.getCompanyAddress());
        job.setCompanyWebsite(dto.getCompanyWebsite());
        job.setCompanyDescription(dto.getCompanyDescription());
        job.setEmployer(employer);
        job.setPostedDate(java.time.LocalDate.now());

        Job savedJob = jobRepository.save(job);

        logger.info("Job Posted Successfully");

        return savedJob;
    }

    @Override
    public Job updateJob(int jobId, JobDTO dto) {

        logger.info("Updating Job");

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Job Not Found"));

        job.setJobTitle(dto.getJobTitle());
        job.setCompanyName(dto.getCompanyName());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setSalary(dto.getSalary());
        job.setExperience(dto.getExperience());
        job.setJobType(dto.getJobType());
        job.setRequiredSkills(dto.getRequiredSkills());
        job.setStatus(dto.getStatus() != null ? dto.getStatus() : "Active");
        job.setCompanyEmail(dto.getCompanyEmail());
        job.setCompanyPhone(dto.getCompanyPhone());
        job.setCompanyAddress(dto.getCompanyAddress());
        job.setCompanyWebsite(dto.getCompanyWebsite());
        job.setCompanyDescription(dto.getCompanyDescription());

        Job updatedJob = jobRepository.save(job);

        logger.info("Job Updated Successfully");

        return updatedJob;
    }

    private JobDTO mapToJobDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setJobId(job.getJobId());
        dto.setJobTitle(job.getJobTitle());
        dto.setCompanyName(job.getCompanyName());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setSalary(job.getSalary());
        dto.setExperience(job.getExperience());
        dto.setJobType(job.getJobType());
        dto.setRequiredSkills(job.getRequiredSkills());
        dto.setStatus(job.getStatus());
        if (job.getEmployer() != null) {
            dto.setEmployerId(job.getEmployer().getUserId());
        }
        dto.setCompanyEmail(job.getCompanyEmail() != null ? job.getCompanyEmail() : (job.getEmployer() != null ? job.getEmployer().getEmail() : null));
        dto.setCompanyPhone(job.getCompanyPhone() != null ? job.getCompanyPhone() : (job.getEmployer() != null ? job.getEmployer().getPhone() : null));
        dto.setCompanyAddress(job.getCompanyAddress() != null ? job.getCompanyAddress() : (job.getEmployer() != null ? job.getEmployer().getAddress() : null));
        dto.setCompanyWebsite(job.getCompanyWebsite() != null ? job.getCompanyWebsite() : (job.getEmployer() != null ? job.getEmployer().getWebsite() : null));
        dto.setCompanyDescription(job.getCompanyDescription() != null ? job.getCompanyDescription() : (job.getEmployer() != null ? job.getEmployer().getDescription() : null));
        dto.setPostedDate(job.getPostedDate());
        return dto;
    }

    @Override
    public JobDTO getJobById(int jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Job Not Found"));

        return mapToJobDTO(job);
    }

    @Override
    public List<JobDTO> getJobsByEmployer(int employerId) {

        List<Job> jobs = jobRepository.findByEmployerUserId(employerId);

        List<JobDTO> list = new ArrayList<>();

        for (Job job : jobs) {
            list.add(mapToJobDTO(job));
        }

        return list;
    }

    @Override
    public List<JobDTO> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> list = new ArrayList<>();
        for (Job job : jobs) {
            list.add(mapToJobDTO(job));
        }
        return list;
    }

    @Override
    public List<JobDTO> searchByLocation(String location) {

        List<Job> jobs = jobRepository.findByLocationContainingIgnoreCase(location);
        List<JobDTO> list = new ArrayList<>();
        for (Job job : jobs) {
            list.add(mapToJobDTO(job));
        }
        return list;
    }

    @Override
    public List<JobDTO> searchByTitle(String title) {

        List<Job> jobs = jobRepository.findByJobTitleContainingIgnoreCase(title);
        List<JobDTO> list = new ArrayList<>();
        for (Job job : jobs) {
            list.add(mapToJobDTO(job));
        }
        return list;
    }

    @Override
    public void deleteJob(int jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Job Not Found"));

        jobRepository.delete(job);

        logger.info("Job Deleted Successfully");
    }

}