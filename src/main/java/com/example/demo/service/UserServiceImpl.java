package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository repo;

    @Override
    public User addUser(UserDTO dto) {

        User user = new User();

        user.setUserId(dto.getUserId());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        return repo.save(user);
    }

    @Override
    public User updateUser(UserDTO dto) {

        User user = new User();

        user.setUserId(dto.getUserId());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        return repo.save(user);
    }

    @Override
    public UserDTO getUserById(int id) {

        User user = repo.findById(id)
                .orElseThrow(() ->
                new UserNotFoundException(
                        "User Not Found With Id : " + id));

        UserDTO dto = new UserDTO();

        dto.setUserId(user.getUserId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());

        return dto;
    }
    @Override
    public void deleteUser(int id) {
          
    	User user = repo.findById(id)
                .orElseThrow(() ->
                new UserNotFoundException(
                        "User Not Found With Id : " + id));
        repo.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {

        return repo.findAll();
    }
}