package com.hexaware.career.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.career.dto.UserDTO;
import com.hexaware.career.entity.User;
import com.hexaware.career.service.IUserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService service;

    @Autowired
    private TestDbInitializer dbInitializer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbInitializer.init();
    }

    @Test
    void testRegisterUser() {

        UserDTO dto = new UserDTO();

        dto.setFullName("Uma");
        dto.setEmail("uma_" + System.currentTimeMillis() + "@gmail.com");
        dto.setPassword("uma123");
        dto.setPhone("9876543210");

        User user = service.registerUser(dto);

        assertNotNull(user);
        assertEquals("Uma", user.getFullName());
    }

    @Test
    void testGetUserById() {

        UserDTO user = service.getUserById(1);

        assertNotNull(user);
    }

    @Test
    void testUpdateUser() {

        UserDTO dto = new UserDTO();

        dto.setFullName("Uma Updated");
        dto.setPhone("9999999999");

        User user = service.updateUser(1, dto);

        assertNotNull(user);
        assertEquals("Uma Updated", user.getFullName());
    }

    @Test
    void testGetAllUsers() {

        List<User> users = service.getAllUsers();

        assertNotNull(users);
    }

    @Test
    void testDeleteUser() {

        service.deleteUser(1);

        assertTrue(true);
    }
}