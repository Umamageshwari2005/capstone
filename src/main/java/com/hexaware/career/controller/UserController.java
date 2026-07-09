package com.hexaware.career.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.career.dto.UserDTO;
import com.hexaware.career.entity.User;
import com.hexaware.career.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    // View User Profile
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {

        return ResponseEntity.ok(service.getUserById(userId));
    }

    // Update User Profile
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable int userId,
            @Valid @RequestBody UserDTO dto) {

        return ResponseEntity.ok(service.updateUser(userId, dto));
    }

    // View All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(service.getAllUsers());
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {

        service.deleteUser(userId);

        return ResponseEntity.ok("User Deleted Successfully");
    }

}