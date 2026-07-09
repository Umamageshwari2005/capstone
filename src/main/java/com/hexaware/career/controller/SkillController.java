package com.hexaware.career.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.dto.SkillDTO;
import com.hexaware.career.entity.Skill;
import com.hexaware.career.service.ISkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*")
public class SkillController {

    private final ISkillService service;

    public SkillController(ISkillService service) {
        this.service = service;
    }

    // Add Skill
    @PostMapping
    public ResponseEntity<Skill> addSkill(
            @Valid @RequestBody SkillDTO dto) {

        return new ResponseEntity<>(
                service.addSkill(dto),
                HttpStatus.CREATED);
    }

    // Update Skill
    @PutMapping("/{skillId}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable int skillId,
            @Valid @RequestBody SkillDTO dto) {

        return ResponseEntity.ok(
                service.updateSkill(skillId, dto));
    }

    // Get Skill By Id
    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO> getSkillById(
            @PathVariable int skillId) {

        return ResponseEntity.ok(
                service.getSkillById(skillId));
    }

    // Get Skills By User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillDTO>> getSkillsByUser(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                service.getSkillsByUser(userId));
    }

    // Delete Skill
    @DeleteMapping("/{skillId}")
    public ResponseEntity<String> deleteSkill(
            @PathVariable int skillId) {

        service.deleteSkill(skillId);

        return ResponseEntity.ok("Skill Deleted Successfully");
    }

    // Get All Skills
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {

        return ResponseEntity.ok(
                service.getAllSkills());
    }

}