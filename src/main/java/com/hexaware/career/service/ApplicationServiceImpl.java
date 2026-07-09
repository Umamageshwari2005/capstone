package com.hexaware.career.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.career.dto.ApplicantDetailsDTO;
import com.hexaware.career.dto.ApplicationDTO;
import com.hexaware.career.entity.Application;
import com.hexaware.career.entity.Job;
import com.hexaware.career.entity.Notification;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.ApplicationNotFoundException;
import com.hexaware.career.exception.JobNotFoundException;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.ApplicationRepository;
import com.hexaware.career.repository.JobRepository;
import com.hexaware.career.repository.NotificationRepository;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApplicationServiceImpl implements IApplicationService {

    private static final Logger logger =
            LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final NotificationRepository notificationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  UserRepository userRepository,
                                  JobRepository jobRepository,
                                  NotificationRepository notificationRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Application applyJob(ApplicationDTO dto) {

        logger.info("User Applying For Job");

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() ->
                        new JobNotFoundException("Job Not Found"));

        Application application = new Application();

        application.setStatus(dto.getStatus() != null && !dto.getStatus().isEmpty() ? dto.getStatus() : "Applied");
        application.setUser(user);
        application.setJob(job);
        application.setAppliedDate(java.time.LocalDate.now());

        Application savedApplication =
                applicationRepository.save(application);

        logger.info("Application Submitted Successfully");

        return savedApplication;
    }

    @Override
    public Application updateApplication(int applicationId,
                                         ApplicationDTO dto) {

        logger.info("Updating Application");

        Application application =
                applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application Not Found"));

        application.setStatus(dto.getStatus());

        Application updatedApplication =
                applicationRepository.save(application);

        // Auto-create Notification for User
        try {
            Notification notification = new Notification();
            notification.setUser(application.getUser());
            notification.setRead(false);
            notification.setCreatedAt(java.time.LocalDateTime.now());
            notification.setType("Application Status Update");

            String message = "Your application for " + application.getJob().getJobTitle() + " has been updated.";
            String status = dto.getStatus();
            if ("Applied".equalsIgnoreCase(status) || "Application Received".equalsIgnoreCase(status)) {
                message = "Your application for " + application.getJob().getJobTitle() + " has been received.";
            } else if ("Under Review".equalsIgnoreCase(status)) {
                message = "Your application for " + application.getJob().getJobTitle() + " is under review.";
            } else if ("Shortlisted".equalsIgnoreCase(status)) {
                message = "Congratulations! You have been shortlisted for " + application.getJob().getJobTitle() + ".";
            } else if ("Interview Scheduled".equalsIgnoreCase(status)) {
                message = "Your interview for " + application.getJob().getJobTitle() + " has been scheduled.";
            } else if ("Selected".equalsIgnoreCase(status)) {
                message = "Congratulations! You have been selected for " + application.getJob().getJobTitle() + ".";
            } else if ("Rejected".equalsIgnoreCase(status)) {
                message = "We regret to inform you that your application for " + application.getJob().getJobTitle() + " has been rejected.";
            }
            notification.setMessage(message);
            notificationRepository.save(notification);
        } catch (Exception e) {
            logger.error("Failed to create status notification", e);
        }

        logger.info("Application Updated Successfully");

        return updatedApplication;
    }

    private ApplicationDTO mapToApplicationDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(application.getApplicationId());
        dto.setStatus(application.getStatus());
        dto.setUserId(application.getUser().getUserId());
        dto.setJobId(application.getJob().getJobId());

        dto.setJobTitle(application.getJob().getJobTitle());
        dto.setCompanyName(application.getJob().getCompanyName());
        if (application.getJob().getEmployer() != null) {
            dto.setCompanyId(application.getJob().getEmployer().getUserId());
        }
        dto.setJobDescription(application.getJob().getDescription());
        dto.setAppliedDate(application.getAppliedDate() != null ? application.getAppliedDate() : java.time.LocalDate.now());
        dto.setEmployerUpdates(application.getStatus());

        return dto;
    }

    @Override
    public ApplicationDTO getApplicationById(int applicationId) {

        Application application =
                applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application Not Found"));

        return mapToApplicationDTO(application);
    }

    @Override
    public List<ApplicationDTO> getApplicationsByUser(int userId) {

        List<Application> applications =
                applicationRepository.findByUserUserId(userId);

        List<ApplicationDTO> list = new ArrayList<>();

        for (Application application : applications) {
            list.add(mapToApplicationDTO(application));
        }

        return list;
    }

    @Override
    public List<ApplicationDTO> getApplicationsByJob(int jobId) {

        List<Application> applications =
                applicationRepository.findByJobJobId(jobId);

        List<ApplicationDTO> list = new ArrayList<>();

        for (Application application : applications) {
            list.add(mapToApplicationDTO(application));
        }

        return list;
    }

    @Override
    public List<ApplicantDetailsDTO> getApplicantsByEmployer(int employerId) {
        List<Application> applications = applicationRepository.findByJobEmployerUserId(employerId);
        List<ApplicantDetailsDTO> list = new ArrayList<>();
        for (Application app : applications) {
            list.add(mapToApplicantDetailsDTO(app));
        }
        return list;
    }

    private ApplicantDetailsDTO mapToApplicantDetailsDTO(Application app) {
        ApplicantDetailsDTO dto = new ApplicantDetailsDTO();
        dto.setApplicationId(app.getApplicationId());
        dto.setStatus(app.getStatus());
        dto.setAppliedDate(app.getAppliedDate() != null ? app.getAppliedDate() : java.time.LocalDate.now());

        User user = app.getUser();
        if (user != null) {
            dto.setUserId(user.getUserId());
            dto.setFullName(user.getFullName());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setGender(user.getGender());
            dto.setDob(user.getDob());
            dto.setAddress(user.getAddress());
            dto.setDegree(user.getDegree());
            dto.setCollege(user.getCollege());
            dto.setCgpa(user.getCgpa());
            dto.setPassedOutYear(user.getPassedOutYear());
            dto.setExperience(user.getExperience());
            dto.setCurrentCompany(user.getCurrentCompany());
            dto.setProjects(user.getProjects());
            dto.setCertifications(user.getCertifications());
            dto.setLinkedin(user.getLinkedin());
            dto.setGithub(user.getGithub());

            // map skills
            if (user.getSkills() != null) {
                dto.setSkills(user.getSkills().stream().map(skill -> {
                    com.hexaware.career.dto.SkillDTO sdto = new com.hexaware.career.dto.SkillDTO();
                    sdto.setSkillId(skill.getSkillId());
                    sdto.setSkillName(skill.getSkillName());
                    sdto.setUserId(user.getUserId());
                    return sdto;
                }).collect(java.util.stream.Collectors.toList()));
            } else {
                dto.setSkills(new ArrayList<>());
            }

            // map resumes
            if (user.getResumes() != null) {
                dto.setResumes(user.getResumes().stream().map(res -> {
                    com.hexaware.career.dto.ResumeDTO rdto = new com.hexaware.career.dto.ResumeDTO();
                    rdto.setResumeId(res.getResumeId());
                    rdto.setResumeName(res.getResumeName());
                    rdto.setResumeUrl("/api/resumes/download/" + res.getResumeId());
                    rdto.setUserId(user.getUserId());
                    return rdto;
                }).collect(java.util.stream.Collectors.toList()));
            } else {
                dto.setResumes(new ArrayList<>());
            }
        }

        Job job = app.getJob();
        if (job != null) {
            dto.setJobId(job.getJobId());
            dto.setJobTitle(job.getJobTitle());
            dto.setCompanyName(job.getCompanyName());
        }

        return dto;
    }

    @Override
    public List<Application> getAllApplications() {

        return applicationRepository.findAll();
    }

    @Override
    public void deleteApplication(int applicationId) {

        Application application =
                applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application Not Found"));

        applicationRepository.delete(application);

        logger.info("Application Deleted Successfully");
    }

    @Override
    public ApplicantDetailsDTO getApplicantDetailsByApplicationId(int applicationId) {
        Application application =
                applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application Not Found"));

        return mapToApplicantDetailsDTO(application);
    }

    @Override
    public List<ApplicantDetailsDTO> searchApplicantsByApplicantId(int employerId, int applicantId) {
        List<Application> applications = applicationRepository.findByJobEmployerUserId(employerId);
        List<ApplicantDetailsDTO> list = new ArrayList<>();
        for (Application app : applications) {
            if (app.getUser() != null && app.getUser().getUserId() == applicantId) {
                list.add(mapToApplicantDetailsDTO(app));
            }
        }
        return list;
    }

}