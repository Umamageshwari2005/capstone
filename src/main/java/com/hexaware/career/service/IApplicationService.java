package com.hexaware.career.service;

import java.util.List;

import com.hexaware.career.dto.ApplicantDetailsDTO;
import com.hexaware.career.dto.ApplicationDTO;
import com.hexaware.career.entity.Application;

public interface IApplicationService {

    Application applyJob(ApplicationDTO dto);

    Application updateApplication(int applicationId, ApplicationDTO dto);

    ApplicationDTO getApplicationById(int applicationId);

    List<ApplicationDTO> getApplicationsByUser(int userId);

    List<ApplicationDTO> getApplicationsByJob(int jobId);

    List<ApplicantDetailsDTO> getApplicantsByEmployer(int employerId);

    List<Application> getAllApplications();

    void deleteApplication(int applicationId);

    com.hexaware.career.dto.ApplicantDetailsDTO getApplicantDetailsByApplicationId(int applicationId);

    List<com.hexaware.career.dto.ApplicantDetailsDTO> searchApplicantsByApplicantId(int employerId, int applicantId);

}