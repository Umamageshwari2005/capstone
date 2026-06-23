package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.entity.Application;
import com.example.demo.service.IApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    IApplicationService service;

    @PostMapping("/add")
    public Application addApplication(@Valid @RequestBody ApplicationDTO dto) {

        return service.addApplication(dto);
    }

    @PutMapping("/update")
    public Application updateApplication(@Valid @RequestBody ApplicationDTO dto) {

        return service.updateApplication(dto);
    }

    @GetMapping("/getbyid/{id}")
    public ApplicationDTO getApplicationById(@PathVariable int id) {

        return service.getApplicationById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApplication(@PathVariable int id) {

        service.deleteApplication(id);

        return "Application Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<Application> getAllApplications() {

        return service.getAllApplications();
    }
}