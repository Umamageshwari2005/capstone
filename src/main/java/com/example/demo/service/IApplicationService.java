package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.entity.Application;

public interface IApplicationService {

    Application addApplication(ApplicationDTO dto);

    Application updateApplication(ApplicationDTO dto);

    ApplicationDTO getApplicationById(int id);

    void deleteApplication(int id);

    List<Application> getAllApplications();
}