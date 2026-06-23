package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.entity.Application;
import com.example.demo.exception.ApplicationNotFoundException;
import com.example.demo.repository.ApplicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    ApplicationRepository repo;

    @Override
    public Application addApplication(ApplicationDTO dto) {

        Application app = new Application();

        app.setApplicationId(dto.getApplicationId());
        app.setStatus(dto.getStatus());

        return repo.save(app);
    }

    @Override
    public Application updateApplication(ApplicationDTO dto) {

        Application app = new Application();

        app.setApplicationId(dto.getApplicationId());
        app.setStatus(dto.getStatus());

        return repo.save(app);
    }

    @Override
    public ApplicationDTO getApplicationById(int id) {

    	

        ApplicationDTO dto = new ApplicationDTO();

        dto.setApplicationId(dto.getApplicationId());
        dto.setStatus(dto.getStatus());

        return dto;
    }

    @Override
    public void deleteApplication(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Application> getAllApplications() {
        return repo.findAll();
    }
}