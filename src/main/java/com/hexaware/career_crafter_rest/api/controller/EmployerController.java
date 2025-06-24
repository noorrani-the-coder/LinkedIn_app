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

import com.hexaware.career_crafter_rest.api.dto.EmployerDTO;
import com.hexaware.career_crafter_rest.api.dto.EmployerJobVO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.Employer;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;
import com.hexaware.career_crafter_rest.api.service.EmployerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/employers")
@Slf4j
public class EmployerController {

    private static final Logger log = LoggerFactory.getLogger(EmployerController.class);

    @Autowired
    private EmployerServiceImpl employerService;

    @GetMapping("/getallemp")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Employer> getAllEmployers() {
        log.info("Fetching all employers (ADMIN access)");
        List<Employer> employers = employerService.getAllEmployers();
        log.info("Total employers found: {}", employers.size());
        return employers;
    }

    @PostMapping("/addemp")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER')")
    public Employer addEmployer(@RequestBody EmployerDTO employerDTO) {
        log.info("Adding new employer: {}", employerDTO.getCompanyName());
        Employer added = employerService.addEmployer(employerDTO);
        log.info("Employer added with ID: {}", added.getEmployerId());
        return added;
    }

    @PutMapping("/updateemp/{eid}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER')")
    public Employer updateEmployer(@PathVariable int eid, @RequestBody EmployerDTO employerDTO) {
        log.info("Updating employer with ID: {}", eid);
        Employer updated = employerService.updateEmployer(eid, employerDTO);
        log.info("Employer updated: {}", updated.getEmployerId());
        return updated;
    }

    @GetMapping("/getbyid/{eid}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER')")
    public Employer getEmployerById(@PathVariable int eid) {
        log.info("Fetching employer by ID: {}", eid);
        Employer emp = employerService.getEmployerById(eid);
        log.info("Employer found: {}", emp != null ? emp.getCompanyName() : "Not Found");
        return emp;
    }

    @DeleteMapping("/deleteById/{eid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteEmployerById(@PathVariable int eid) {
        log.info("Deleting employer with ID: {}", eid);
        String result = employerService.deleteEmployerById(eid);
        log.info("Delete result: {}", result);
        return result;
    }

    @PostMapping("/addjob")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public JobsDTO addJob(@RequestBody JobsDTO jobDTO) {
        log.info("Adding new job for employer: {}", jobDTO.getEmployerId());
        JobsDTO addedJob = employerService.addJobForEmployer(jobDTO);
        log.info("Job added with ID: {}", addedJob.getJobid());
        return addedJob;
    }

    @PutMapping("/updatejob/{jobId}")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public JobsDTO updateJob(@PathVariable int jobId, @RequestBody JobsDTO jobDTO) {
        log.info("Updating job with ID: {}", jobId);
        JobsDTO updatedJob = employerService.updateJobForEmployer(jobId, jobDTO);
        log.info("Job updated successfully: {}", updatedJob.getJobid());
        return updatedJob;
    }

    @DeleteMapping("/deletejob/{jobId}")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public String deleteJob(@PathVariable int jobId) {
        log.info("Deleting job with ID: {}", jobId);
        String result = employerService.deleteJobForEmployer(jobId);
        log.info("Job delete result: {}", result);
        return result;
    }

    @GetMapping("/employerwithjob/{employerId}/jobs")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public EmployerJobVO getEmployerWithJob(@PathVariable int employerId) {
        log.info("Fetching employer {} ", employerId);
        return employerService.getEmployerAndJob(employerId);
    }
    
    @GetMapping("/getalljobs")
    @PreAuthorize("hasAnyAuthority('JOB_SEEKER', 'EMPLOYER')")
    public List<JobsDTO> getAllJobsFromJobMicroservice() {
        return employerService.getAllJobs();
    }
    
    @GetMapping("/getempbyUserid/{userid}")
    @PreAuthorize("hasAnyAuthority('EMPLOYER', 'ADMIN')")
    public ResponseEntity<Employer> findByUserid(@PathVariable int userid) {
        Employer employer = employerService.getEmployerByUserId(userid);
        if (employer != null) {
            return ResponseEntity.ok(employer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}