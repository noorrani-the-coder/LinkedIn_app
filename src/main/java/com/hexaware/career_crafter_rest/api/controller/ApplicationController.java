package com.hexaware.career_crafter_rest.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
import org.springframework.web.client.RestTemplate;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.dto.EnrichedApplicationDTO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.ApplicationC;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;
import com.hexaware.career_crafter_rest.api.repository.ApplicationRepository;
import com.hexaware.career_crafter_rest.api.service.ApplicationServiceImpl;
import com.hexaware.career_crafter_rest.api.service.JobSeekerServiceImpl;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/App")
public class ApplicationController {

    @Autowired
    private ApplicationServiceImpl ser;

    @GetMapping("/getallapp")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER')")
    public List<ApplicationC> getallApp() {
        return ser.getallApplication();
    }

    @PostMapping("/addapp")
    @PreAuthorize("hasAuthority('JOB_SEEKER')")
    public ApplicationC addApp(@RequestBody ApplicationDTO app) {
        return ser.addApplication(app);
    }

    @PutMapping("/updateapp/{appid}")
    @PreAuthorize("hasAnyAuthority('EMPLOYER', 'ADMIN')")
    public ApplicationC updateApp(@PathVariable int appid, @RequestBody ApplicationDTO app) {
        return ser.updateApplication(appid, app);
    }

    @GetMapping("/getbyid/{appid}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER', 'JOB_SEEKER')")
    public ApplicationC getByAppID(@PathVariable int appid) {
        return ser.getbyId(appid);
    }

    @DeleteMapping("/deleteById/{appid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteById(@PathVariable int appid) {
        return ser.deleteByID(appid);
    }
    @Autowired
    private JobSeekerServiceImpl jsservice;
    
    @GetMapping("/applications/{jsid}")
    @PreAuthorize("hasAuthority('JOB_SEEKER')")
    public List<ApplicationC> getApplications(@PathVariable int jsid) {
        JobSeekers seeker = jsservice.getJobSeekerById(jsid);
        return ser.getApplicationsByJobSeeker(seeker);
    }
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationRepository applicationRepo;

//    @GetMapping("/applications/employer/{employerId}")
//    @PreAuthorize("hasAnyAuthority('EMPLOYER', 'ADMIN')")
//    public List<ApplicationDTO> getApplicationsByEmployer(@PathVariable int employerId) {
//        // 1. Fetch all jobs for the given employer
//        String jobServiceUrl = "http://localhost:8082/api/jobs/getJobEmpId/" + employerId;
//        ResponseEntity<JobsDTO[]> response = restTemplate.getForEntity(jobServiceUrl, JobsDTO[].class);
//
//        List<Integer> jobIds = Arrays.stream(response.getBody())
//                                     .map(JobsDTO::getJobid)
//                                     .collect(Collectors.toList());
//
//        // 2. Fetch all applications and filter based on job IDs
//        return applicationRepo.findAll().stream()
//            .filter(app -> jobIds.contains(app.getJobListing()))
//            .map(app -> new ApplicationDTO(
//                app.getJobSeeker().getJsId(),
//                app.getJobListing(),
//                app.getAppliedDate(),
//                app.getStatus(),
//                app.getResumeFilePath()
//            ))
//            .collect(Collectors.toList());
//    }
    @GetMapping("/applications/employer/{employerId}")
    @PreAuthorize("hasAnyAuthority('EMPLOYER', 'ADMIN')")
    public List<EnrichedApplicationDTO> getApplicationsByEmployer(@PathVariable int employerId) {
        List<ApplicationC> allApplications = applicationRepo.findAll();

        return allApplications.stream()
            .map(app -> {
                JobsDTO job = null;
                try {
                    String jobServiceUrl = "http://localhost:8082/api/jobs/getbyid/" + app.getJobListing();
                    job = restTemplate.getForObject(jobServiceUrl, JobsDTO.class);
                } catch (Exception e) {
                    System.err.println("❌ Error fetching job ID " + app.getJobListing() + ": " + e.getMessage());
                }

                // ✅ Filter only jobs belonging to the employer
                if (job != null && job.getEmployerId() == employerId) {
                    return new EnrichedApplicationDTO(
                        app.getAppid(),
                        app.getAppliedDate(),
                        app.getStatus(),
                        app.getResumeFilePath(),
                        job,
                        app.getJobSeeker()
                    );
                }
                return null; // Skip invalid or mismatched employer
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
