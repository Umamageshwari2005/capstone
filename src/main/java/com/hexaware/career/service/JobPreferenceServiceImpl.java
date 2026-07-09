package com.hexaware.career.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.career.dto.JobPreferenceDTO;
import com.hexaware.career.entity.JobPreference;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.JobPreferenceNotFoundException;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.JobPreferenceRepository;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobPreferenceServiceImpl implements IJobPreferenceService {

    private static final Logger logger =
            LoggerFactory.getLogger(JobPreferenceServiceImpl.class);

    private final JobPreferenceRepository preferenceRepository;
    private final UserRepository userRepository;

    public JobPreferenceServiceImpl(JobPreferenceRepository preferenceRepository,
                                    UserRepository userRepository) {

        this.preferenceRepository = preferenceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public JobPreference addPreference(JobPreferenceDTO dto) {

        logger.info("Adding Job Preference");

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        JobPreference preference = new JobPreference();

        preference.setPreferredRole(dto.getPreferredRole());
        preference.setPreferredLocation(dto.getPreferredLocation());
        preference.setExpectedSalary(dto.getExpectedSalary());
        preference.setUser(user);

        JobPreference savedPreference =
                preferenceRepository.save(preference);

        logger.info("Job Preference Added Successfully");

        return savedPreference;
    }

    @Override
    public JobPreference updatePreference(int preferenceId,
                                          JobPreferenceDTO dto) {

        logger.info("Updating Job Preference");

        JobPreference preference =
                preferenceRepository.findById(preferenceId)
                .orElseThrow(() ->
                        new JobPreferenceNotFoundException(
                                "Job Preference Not Found"));

        preference.setPreferredRole(dto.getPreferredRole());
        preference.setPreferredLocation(dto.getPreferredLocation());
        preference.setExpectedSalary(dto.getExpectedSalary());

        JobPreference updatedPreference =
                preferenceRepository.save(preference);

        logger.info("Job Preference Updated Successfully");

        return updatedPreference;
    }

    @Override
    public JobPreferenceDTO getPreferenceById(int preferenceId) {

        JobPreference preference =
                preferenceRepository.findById(preferenceId)
                .orElseThrow(() ->
                        new JobPreferenceNotFoundException(
                                "Job Preference Not Found"));

        JobPreferenceDTO dto = new JobPreferenceDTO();

        dto.setPreferenceId(preference.getPreferenceId());
        dto.setPreferredRole(preference.getPreferredRole());
        dto.setPreferredLocation(preference.getPreferredLocation());
        dto.setExpectedSalary(preference.getExpectedSalary());
        dto.setUserId(preference.getUser().getUserId());

        return dto;
    }

    @Override
    public List<JobPreferenceDTO> getPreferencesByUser(int userId) {

        List<JobPreference> preferences =
                preferenceRepository.findByUserUserId(userId);

        List<JobPreferenceDTO> list = new ArrayList<>();

        for (JobPreference preference : preferences) {

            JobPreferenceDTO dto = new JobPreferenceDTO();

            dto.setPreferenceId(preference.getPreferenceId());
            dto.setPreferredRole(preference.getPreferredRole());
            dto.setPreferredLocation(preference.getPreferredLocation());
            dto.setExpectedSalary(preference.getExpectedSalary());
            dto.setUserId(userId);

            list.add(dto);
        }

        return list;
    }

    @Override
    public List<JobPreference> getAllPreferences() {

        return preferenceRepository.findAll();
    }

    @Override
    public void deletePreference(int preferenceId) {

        JobPreference preference =
                preferenceRepository.findById(preferenceId)
                .orElseThrow(() ->
                        new JobPreferenceNotFoundException(
                                "Job Preference Not Found"));

        preferenceRepository.delete(preference);

        logger.info("Job Preference Deleted Successfully");
    }

}