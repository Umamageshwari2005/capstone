package com.hexaware.career.service;

import java.util.List;

import com.hexaware.career.dto.JobPreferenceDTO;
import com.hexaware.career.entity.JobPreference;

public interface IJobPreferenceService {

    JobPreference addPreference(JobPreferenceDTO dto);

    JobPreference updatePreference(int preferenceId, JobPreferenceDTO dto);

    JobPreferenceDTO getPreferenceById(int preferenceId);

    List<JobPreferenceDTO> getPreferencesByUser(int userId);

    List<JobPreference> getAllPreferences();

    void deletePreference(int preferenceId);

}