package com.hexaware.career.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.career.entity.User;
import com.hexaware.career.repository.UserRepository;
import com.hexaware.career.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // Register User
    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());
        user.setDegree(request.getDegree());
        user.setCollege(request.getCollege());
        user.setCgpa(request.getCgpa());
        user.setPassedOutYear(request.getPassedOutYear());
        user.setExperience(request.getExperience());
        user.setCurrentCompany(request.getCurrentCompany());
        user.setProjects(request.getProjects());
        user.setCertifications(request.getCertifications());
        user.setLinkedin(request.getLinkedin());
        user.setGithub(request.getGithub());
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    // Login User
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

        // Check Role
        if (!user.getRole().equals(request.getRole())) {
            throw new RuntimeException("Invalid Role");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getRole().name(),
                user.getUserId(),
                user.getFullName());
    }

    // Reset Password
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}