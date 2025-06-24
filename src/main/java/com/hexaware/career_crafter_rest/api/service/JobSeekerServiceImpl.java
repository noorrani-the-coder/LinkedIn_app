package com.hexaware.career_crafter_rest.api.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.dto.JobSeekerDTO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.ApplicationC;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.exception.JobSeekerNotFoundException;
import com.hexaware.career_crafter_rest.api.exception.ResourceNotFoundException;
import com.hexaware.career_crafter_rest.api.exception.UserNotFoundException;
import com.hexaware.career_crafter_rest.api.repository.ApplicationRepository;
import com.hexaware.career_crafter_rest.api.repository.JobSeekerRepository;
import com.hexaware.career_crafter_rest.api.repository.UserRepository;

/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */
@Service
public class JobSeekerServiceImpl implements IJobSeekerService {
	
	@Autowired
	private ApplicationRepository apprepo;

    @Autowired
    private JobSeekerRepository jobSeekerRepo;

    @Autowired
    private UserRepository usersRepo;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JobSeekers addJobSeeker(JobSeekerDTO dto) {
        Users user = usersRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUserId()));

        JobSeekers jobSeeker = new JobSeekers();
        jobSeeker.setUser(user);
        jobSeeker.setFullName(dto.getFullName());
        jobSeeker.setEducation(dto.getEducation());
        jobSeeker.setExperience(dto.getExperience());
        jobSeeker.setSkills(dto.getSkills());
        jobSeeker.setContactInfo(dto.getContactInfo());

        return jobSeekerRepo.save(jobSeeker);
    }

    @Override
    public JobSeekers updateJobSeeker(int jsid, JobSeekerDTO dto) {
        JobSeekers jobSeeker = jobSeekerRepo.findById(jsid)
                .orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with ID: " + jsid));

        Users user = usersRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUserId()));

        jobSeeker.setUser(user);
        jobSeeker.setFullName(dto.getFullName());
        jobSeeker.setEducation(dto.getEducation());
        jobSeeker.setExperience(dto.getExperience());
        jobSeeker.setSkills(dto.getSkills());
        jobSeeker.setContactInfo(dto.getContactInfo());

        return jobSeekerRepo.save(jobSeeker);
    }

    @Override
    public JobSeekers getJobSeekerById(int jsid) {
        return jobSeekerRepo.findById(jsid)
                .orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with ID: " + jsid));
    }

    @Override
    public String deleteJobSeekerById(int jsid) {
        if (!jobSeekerRepo.existsById(jsid)) {
            throw new JobSeekerNotFoundException("JobSeeker not found with ID: " + jsid);
        }
        jobSeekerRepo.deleteById(jsid);
        return "Deleted JobSeeker with ID: " + jsid;
    }

    @Override
    public List<JobSeekers> getAllJobSeekers() {
        return jobSeekerRepo.findAll();
    }
    
    @Override
    public String applyForJob(ApplicationDTO dto) {

        
        JobSeekers seeker = jobSeekerRepo.findById(dto.getJobSeekerId())
                .orElseThrow();

        //  Validate Job ID via Job Microservice (REST call)
        String jobServiceUrl = "http://localhost:8082/api/jobs/getbyid/" + dto.getJobId();
        

        // Step 3: Create Application
        ApplicationC application = new ApplicationC();
        application.setJobSeeker(seeker);
        application.setJobListing(dto.getJobId());
        application.setResumeFilePath(dto.getResumeFilePath());
        application.setStatus("APPLIED");
        application.setAppliedDate(LocalDate.now());

        apprepo.save(application);

        return "Application submitted successfully.";
    }
    
    @Override
    public List<JobsDTO> getAllJobs() {
        String jobServiceUrl = "http://localhost:8082/api/jobs/getalljobs";

        ResponseEntity<JobsDTO[]> response = restTemplate.getForEntity(jobServiceUrl, JobsDTO[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch jobs from Job Microservice");
        }
    }
    
    public JobSeekers getJobSeekerByUserId(int userid) {
        return jobSeekerRepo.findByUserUserid(userid)
            .orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with ID: " + userid));
    }




}
