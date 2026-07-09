package com.hexaware.career.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findByUserUserId(int userId);

    List<Application> findByJobJobId(int jobId);

    @org.springframework.data.jpa.repository.Query("SELECT a FROM Application a WHERE a.job.employer.userId = :employerId")
    List<Application> findByJobEmployerUserId(@org.springframework.data.repository.query.Param("employerId") int employerId);

}