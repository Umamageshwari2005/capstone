package com.hexaware.career.service;

import java.util.List;

import com.hexaware.career.dto.UserDTO;
import com.hexaware.career.entity.User;

public interface IUserService {

    
    User registerUser(UserDTO dto);

    
    User updateUser(int userId, UserDTO dto);

   
    UserDTO getUserById(int userId);

    
    List<User> getAllUsers();

   
    void deleteUser(int userId);

   
    User findByEmail(String email);

}