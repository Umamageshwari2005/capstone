package com.hexaware.career.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    List<Resume> findByUserUserId(int userId);

}