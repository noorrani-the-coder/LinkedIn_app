package com.hexaware.career_crafter_rest.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.entity.ApplicationC;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;

import com.hexaware.career_crafter_rest.api.repository.ApplicationRepository;
import com.hexaware.career_crafter_rest.api.repository.JobSeekerRepository;


public class ApplicationCServiceImplTest {

	

	    @InjectMocks
	    private ApplicationServiceImpl applicationService;

	    @Mock
	    private ApplicationRepository apprepo;

	    @Mock
	    private JobSeekerRepository jobSeekerRepo;

	  

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testAddApplication_Success() {
	        ApplicationDTO dto = new ApplicationDTO();
	        dto.setJobSeekerId(1);
	        dto.setJobId(1);
	        dto.setAppliedDate(LocalDate.now());
	        dto.setStatus("APPLIED");
	        dto.setResumeFilePath("resume.pdf");

	        JobSeekers jobSeeker = new JobSeekers();
	        jobSeeker.setJsId(1);
	        
	        

	        when(jobSeekerRepo.findById(1)).thenReturn(Optional.of(jobSeeker));
	        
	        when(apprepo.save(any(ApplicationC.class))).thenAnswer(i -> i.getArguments()[0]);

	        ApplicationC result = applicationService.addApplication(dto);
	        assertNotNull(result);
	        assertEquals("APPLIED", result.getStatus());
	    }
	
}

