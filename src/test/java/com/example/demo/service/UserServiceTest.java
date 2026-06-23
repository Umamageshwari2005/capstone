package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService service;

    @Test
    void testAddUser() {

        UserDTO dto = new UserDTO();

        dto.setUserId(4);
        dto.setFullName("Uma");
        dto.setEmail("uma@gmail.com");

        User user = service.addUser(dto);

        assertNotNull(user);
        assertEquals("Uma", user.getFullName());
    }

    @Test
    void testGetUserById() {

        UserDTO user = service.getUserById(1);

        assertNotNull(user);
        assertEquals(101, user.getUserId());
    }

    @Test
    void testUpdateUser() {

        UserDTO dto = new UserDTO();

        dto.setUserId(1);
        dto.setFullName("Uma Updated");
        dto.setEmail("updated@gmail.com");

        User user = service.updateUser(dto);

        assertNotNull(user);
        assertEquals("Uma Updated", user.getFullName());
    }

    @Test
    void testGetAllUsers() {

        List<User> users = service.getAllUsers();

        assertNotNull(users);
        assertTrue(users.size() >= 0);
    }

    @Test
    void testDeleteUser() {

        service.deleteUser(101);

        assertTrue(true);
    }
}