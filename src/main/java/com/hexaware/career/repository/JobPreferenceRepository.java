package com.hexaware.career.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career.entity.JobPreference;

public interface JobPreferenceRepository extends JpaRepository<JobPreference, Integer> {

    List<JobPreference> findByUserUserId(int userId);

}