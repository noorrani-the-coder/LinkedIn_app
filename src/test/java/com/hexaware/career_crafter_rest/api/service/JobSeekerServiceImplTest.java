package com.hexaware.career_crafter_rest.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.career_crafter_rest.api.dto.JobSeekerDTO;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.repository.JobSeekerRepository;
import com.hexaware.career_crafter_rest.api.repository.UserRepository;

public class JobSeekerServiceImplTest {
	@InjectMocks
    private JobSeekerServiceImpl jobSeekerService;

    @Mock
    private JobSeekerRepository jobSeekerRepo;

    @Mock
    private UserRepository userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddJobSeeker_Success() {
        JobSeekerDTO dto = new JobSeekerDTO();
        dto.setUserId(1);
        dto.setFullName("Yamini");

        Users user = new Users();
        user.setUserid(1);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(jobSeekerRepo.save(any(JobSeekers.class))).thenAnswer(i -> i.getArguments()[0]);

        JobSeekers result = jobSeekerService.addJobSeeker(dto);
        assertNotNull(result);
        assertEquals("Yamini", result.getFullName());
    }

}
