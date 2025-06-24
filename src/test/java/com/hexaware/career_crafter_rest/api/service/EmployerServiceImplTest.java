package com.hexaware.career_crafter_rest.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.career_crafter_rest.api.dto.EmployerDTO;
import com.hexaware.career_crafter_rest.api.entity.Employer;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.repository.EmployerRepository;
import com.hexaware.career_crafter_rest.api.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployerServiceImplTest {

    @InjectMocks
    private EmployerServiceImpl employerService;

    @Mock
    private EmployerRepository employerRepo;

    @Mock
    private UserRepository userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployer_Success() {
        EmployerDTO dto = new EmployerDTO();
        dto.setUserId(1);
        dto.setCompanyName("Tech Ltd");

        Users user = new Users();
        user.setUserid(1);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(employerRepo.save(any(Employer.class))).thenAnswer(i -> i.getArguments()[0]);

        Employer result = employerService.addEmployer(dto);
        assertNotNull(result);
        assertEquals("Tech Ltd", result.getCompanyName());
    }
}
