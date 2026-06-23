package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.JobPreference;

public interface JobPreferenceRepository
extends JpaRepository<JobPreference,Integer>{

}