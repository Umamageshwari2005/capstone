package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    IUserService service;

    @PostMapping("/add")
    public User addUser(@RequestBody UserDTO dto) {

        return service.addUser(dto);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody UserDTO dto) {

        return service.updateUser(dto);
    }

    @GetMapping("/getbyid/{id}")
    public UserDTO getUserById(@PathVariable int id) {

        return service.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {

        service.deleteUser(id);

        return "User Deleted Successfully";
    }

    @GetMapping("/getall")
    public List<User> getAllUsers() {

        return service.getAllUsers();
    }
}