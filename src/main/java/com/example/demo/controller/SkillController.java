package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;
import com.example.demo.service.ISkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    ISkillService service;

    @PostMapping("/add")
    public Skill addSkill(@RequestBody SkillDTO dto) {

        return service.addSkill(dto);
    }

    @PutMapping("/update")
    public Skill updateSkill(@RequestBody SkillDTO dto) {

        return service.updateSkill(dto);
    }

    @GetMapping("/getbyid/{id}")
    public SkillDTO getSkillById(@PathVariable int id) {

        return service.getSkillById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSkill(@PathVariable int id) {

        service.deleteSkill(id);

        return "Skill Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<Skill> getAllSkills() {

        return service.getAllSkills();
    }
}