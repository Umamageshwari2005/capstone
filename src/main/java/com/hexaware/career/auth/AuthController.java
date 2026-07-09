package com.hexaware.career.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register User
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    // Logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        return ResponseEntity.ok("Logout Successful");
    }

    // Forgot/Reset Password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.resetPassword(request.getEmail(), request.getNewPassword());
        return ResponseEntity.ok("Password Reset Successful");
    }

}