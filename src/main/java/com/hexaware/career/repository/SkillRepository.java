package com.hexaware.career.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    List<Skill> findByUserUserId(int userId);

}