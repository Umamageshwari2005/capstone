package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.JobPreferenceDTO;
import com.example.demo.entity.JobPreference;

public interface IJobPreferenceService {

    JobPreference addPreference(JobPreferenceDTO dto);

    JobPreference updatePreference(JobPreferenceDTO dto);

    JobPreferenceDTO getPreferenceById(int id);

    void deletePreference(int id);

    List<JobPreference> getAllPreferences();
}