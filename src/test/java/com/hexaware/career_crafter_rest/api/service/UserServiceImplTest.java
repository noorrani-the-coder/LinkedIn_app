package com.hexaware.career_crafter_rest.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.career_crafter_rest.api.dto.UsersDTO;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepo;
    
    @Mock
    private PasswordEncoder passwordEncoder; 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser_Success() {
        UsersDTO userDto = new UsersDTO();
        userDto.setUsername("yamini");
        userDto.setEmail("yamini@example.com");
        userDto.setPassword("plainPassword");

        Users userEntity = new Users();
        userEntity.setUsername("yamini");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword"); // Mock password encoding
        when(userRepo.save(any(Users.class))).thenReturn(userEntity); // Mock save

        String result = userService.addUsers(userDto);

        assertNotNull(result);
        assertEquals("User added successfully", result);
    }



}