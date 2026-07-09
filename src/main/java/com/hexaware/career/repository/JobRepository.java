package com.hexaware.career.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByEmployerUserId(int employerId);

    List<Job> findByLocationContainingIgnoreCase(String location);

    List<Job> findByJobTitleContainingIgnoreCase(String title);

}