package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JobPreferenceDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.JobPreference;
import com.example.demo.entity.User;
import com.example.demo.exception.JobPreferenceNotFoundException;
import com.example.demo.repository.JobPreferenceRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobPreferenceServiceImpl
implements IJobPreferenceService {

    @Autowired
    JobPreferenceRepository repo;

    @Override
    public JobPreference addPreference(JobPreferenceDTO dto) {

        JobPreference pref = new JobPreference();

        pref.setPreferenceId(dto.getPreferenceId());
        pref.setPreferredRole(dto.getPreferredRole());
        pref.setLocation(dto.getLocation());

        return repo.save(pref);
    }

    @Override
    public JobPreference updatePreference(JobPreferenceDTO dto) {

        JobPreference pref = new JobPreference();

        pref.setPreferenceId(dto.getPreferenceId());
        pref.setPreferredRole(dto.getPreferredRole());
        pref.setLocation(dto.getLocation());

        return repo.save(pref);
    }

    @Override
    public JobPreferenceDTO getPreferenceById(int id) {

    	JobPreference pref = repo.findById(id)
                .orElseThrow(() ->
                new JobPreferenceNotFoundException(
                        "Preference Not Found With Id : " + id));

        JobPreferenceDTO dto = new JobPreferenceDTO();

        dto.setPreferenceId(pref.getPreferenceId());
        dto.setPreferredRole(pref.getPreferredRole());
        dto.setLocation(pref.getLocation());

        return dto;
    }

    @Override
    public void deletePreference(int id) {
    	
    	JobPreference pref = repo.findById(id)
                .orElseThrow(() ->
                new JobPreferenceNotFoundException(
                        "Preference Not Found With Id : " + id));
        repo.deleteById(id);
    }

    @Override
    public List<JobPreference> getAllPreferences() {
        return repo.findAll();
    }
}