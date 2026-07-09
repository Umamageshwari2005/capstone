package com.hexaware.career.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.career.dto.UserDTO;
import com.hexaware.career.entity.User;
import com.hexaware.career.exception.UserNotFoundException;
import com.hexaware.career.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDTO dto) {

        logger.info("Registering User : {}", dto.getEmail());

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setDob(dto.getDob());
        user.setAddress(dto.getAddress());
        user.setDegree(dto.getDegree());
        user.setCollege(dto.getCollege());
        user.setCgpa(dto.getCgpa());
        user.setPassedOutYear(dto.getPassedOutYear());
        user.setExperience(dto.getExperience());
        user.setCurrentCompany(dto.getCurrentCompany());
        user.setProjects(dto.getProjects());
        user.setCertifications(dto.getCertifications());
        user.setLinkedin(dto.getLinkedin());
        user.setGithub(dto.getGithub());
        user.setWebsite(dto.getWebsite());
        user.setDescription(dto.getDescription());
        user.setRole(dto.getRole());

        User savedUser = userRepository.save(user);

        logger.info("User Registered Successfully with Id : {}",
                savedUser.getUserId());

        return savedUser;
    }

    @Override
    public User updateUser(int userId, UserDTO dto) {

        logger.info("Updating User : {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found with Id : " + userId));

        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setDob(dto.getDob());
        user.setAddress(dto.getAddress());
        user.setDegree(dto.getDegree());
        user.setCollege(dto.getCollege());
        user.setCgpa(dto.getCgpa());
        user.setPassedOutYear(dto.getPassedOutYear());
        user.setExperience(dto.getExperience());
        user.setCurrentCompany(dto.getCurrentCompany());
        user.setProjects(dto.getProjects());
        user.setCertifications(dto.getCertifications());
        user.setLinkedin(dto.getLinkedin());
        user.setGithub(dto.getGithub());
        user.setWebsite(dto.getWebsite());
        user.setDescription(dto.getDescription());

        User updatedUser = userRepository.save(user);

        logger.info("User Updated Successfully");

        return updatedUser;
    }

    @Override
    public UserDTO getUserById(int userId) {

        logger.info("Fetching User : {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found with Id : " + userId));

        UserDTO dto = new UserDTO();

        dto.setUserId(user.getUserId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setGender(user.getGender());
        dto.setDob(user.getDob());
        dto.setAddress(user.getAddress());
        dto.setDegree(user.getDegree());
        dto.setCollege(user.getCollege());
        dto.setCgpa(user.getCgpa());
        dto.setPassedOutYear(user.getPassedOutYear());
        dto.setExperience(user.getExperience());
        dto.setCurrentCompany(user.getCurrentCompany());
        dto.setProjects(user.getProjects());
        dto.setCertifications(user.getCertifications());
        dto.setLinkedin(user.getLinkedin());
        dto.setGithub(user.getGithub());
        dto.setWebsite(user.getWebsite());
        dto.setDescription(user.getDescription());
        dto.setRole(user.getRole());

        if (user.getSkills() != null) {
            java.util.List<com.hexaware.career.dto.SkillDTO> skillDTOs = new java.util.ArrayList<>();
            for (com.hexaware.career.entity.Skill skill : user.getSkills()) {
                com.hexaware.career.dto.SkillDTO sDto = new com.hexaware.career.dto.SkillDTO();
                sDto.setSkillId(skill.getSkillId());
                sDto.setSkillName(skill.getSkillName());
                sDto.setUserId(user.getUserId());
                skillDTOs.add(sDto);
            }
            dto.setSkills(skillDTOs);
        }

        if (user.getResumes() != null) {
            java.util.List<com.hexaware.career.dto.ResumeDTO> resumeDTOs = new java.util.ArrayList<>();
            for (com.hexaware.career.entity.Resume resume : user.getResumes()) {
                com.hexaware.career.dto.ResumeDTO rDto = new com.hexaware.career.dto.ResumeDTO();
                rDto.setResumeId(resume.getResumeId());
                rDto.setResumeName(resume.getResumeName());
                rDto.setUserId(user.getUserId());
                rDto.setResumeUrl("/api/resumes/download/" + resume.getResumeId());
                resumeDTOs.add(rDto);
            }
            dto.setResumes(resumeDTOs);
        }

        logger.info("User Details Returned");

        return dto;
    }

    @Override
    public List<User> getAllUsers() {

        logger.info("Fetching All Users");

        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int userId) {

        logger.info("Deleting User : {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found with Id : " + userId));

        userRepository.delete(user);

        logger.info("User Deleted Successfully");
    }

    @Override
    public User findByEmail(String email) {

        logger.info("Searching User By Email : {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found"));
    }

}