package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.JobPreferenceDTO;
import com.example.demo.entity.JobPreference;
import com.example.demo.service.IJobPreferenceService;

@RestController
@RequestMapping("/api/preferences")
public class JobPreferenceController {

    @Autowired
    IJobPreferenceService service;

    @PostMapping("/add")
    public JobPreference addPreference(@RequestBody JobPreferenceDTO dto) {

        return service.addPreference(dto);
    }

    @PutMapping("/update")
    public JobPreference updatePreference(@RequestBody JobPreferenceDTO dto) {

        return service.updatePreference(dto);
    }

    @GetMapping("/getbyid/{id}")
    public JobPreferenceDTO getPreferenceById(@PathVariable int id) {

        return service.getPreferenceById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePreference(@PathVariable int id) {

        service.deletePreference(id);

        return "Job Preference Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<JobPreference> getAllPreferences() {

        return service.getAllPreferences();
    }
}