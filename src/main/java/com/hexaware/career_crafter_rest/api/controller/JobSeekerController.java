package com.hexaware.career_crafter_rest.api.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.dto.JobSeekerDTO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.Employer;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;
import com.hexaware.career_crafter_rest.api.service.JobSeekerServiceImpl;


import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/jobSeeker")
@Slf4j
public class JobSeekerController {
	
	private static final Logger log = LoggerFactory.getLogger(JobSeekerController.class);

    @Autowired
    private JobSeekerServiceImpl jsservice;

    @GetMapping("/getalljobSeeker")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<JobSeekers> getAllJobSeeker() {
        log.info("Fetching all job seekers (accessed by ADMIN)");
        List<JobSeekers> seekers = jsservice.getAllJobSeekers();
        log.info("Total job seekers found: {}", seekers.size());
        return seekers;
    }

    @PostMapping("/addjobSeeker")
    @PreAuthorize("permitAll()")
    public JobSeekers addJobSeekers(@RequestBody JobSeekerDTO jsee) {
        log.info("Adding new job seeker: {}", jsee.getFullName());
        JobSeekers added = jsservice.addJobSeeker(jsee);
        log.info("Job seeker added with ID: {}", added.getJsId());
        return added;
    }

    @PutMapping("/updatejobSeeker/{jsid}")
    @PreAuthorize("hasAnyAuthority('JOB_SEEKER', 'ADMIN')")
    public JobSeekers updateJobSeekers(@PathVariable int jsid, @RequestBody JobSeekerDTO jsee) {
        log.info("Updating job seeker with ID: {}", jsid);
        JobSeekers updated = jsservice.updateJobSeeker(jsid, jsee);
        log.info("Job seeker updated: {}", updated.getJsId());
        return updated;
    }

    @GetMapping("/getbyid/{jsid}") //to get job_seeker by specifying their id
    @PreAuthorize("hasAnyAuthority('JOB_SEEKER', 'ADMIN')")
    public JobSeekers getByJSID(@PathVariable int jsid) {
        log.info("Fetching job seeker with ID: {}", jsid);
        JobSeekers seeker = jsservice.getJobSeekerById(jsid);
        log.info("Job seeker found: {}", seeker != null ? seeker.getFullName() : "Not Found");
        return seeker;
    }

    // to delete job_seeker
    @DeleteMapping("/deletebyid/{jsid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteById(@PathVariable int jsid) {
        log.info("Deleting job seeker with ID: {}", jsid);
        String result = jsservice.deleteJobSeekerById(jsid);
        log.info("Deletion result: {}", result);
        return result;
    }
    //to apply for job
    @PostMapping("/apply")
    @PreAuthorize("hasAuthority('JOB_SEEKER')")
    public ResponseEntity<String> applyToJob(@RequestBody ApplicationDTO dto) {
        String response = jsservice.applyForJob(dto);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getalljobs")
    public List<JobsDTO> getAllJobsFromJobMicroservice() {
        return jsservice.getAllJobs();
    }
    
    @GetMapping("/getJSbyUserid/{userid}")
    @PreAuthorize("hasAnyAuthority('JOB_SEEKER', 'ADMIN')")
    public ResponseEntity<JobSeekers> findByUserid(@PathVariable int userid) {
    	JobSeekers jobseeker= jsservice.getJobSeekerByUserId(userid);
        if (jobseeker != null) {
            return ResponseEntity.ok(jobseeker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}