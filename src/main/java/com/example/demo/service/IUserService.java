package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface IUserService {

    User addUser(UserDTO dto);

    User updateUser(UserDTO dto);

    UserDTO getUserById(int id);

    void deleteUser(int id);

    List<User> getAllUsers();
}