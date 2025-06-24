package com.hexaware.career_crafter_rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.entity.ApplicationC;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;

import com.hexaware.career_crafter_rest.api.exception.ApplicationNotFoundException;

import com.hexaware.career_crafter_rest.api.exception.JobSeekerNotFoundException;
import com.hexaware.career_crafter_rest.api.exception.ResourceNotFoundException;
import com.hexaware.career_crafter_rest.api.repository.ApplicationRepository;
import com.hexaware.career_crafter_rest.api.repository.JobSeekerRepository;
/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private ApplicationRepository apprepo;

    @Autowired
    private JobSeekerRepository jobSeekerRepo;



    @Override
    public ApplicationC addApplication(ApplicationDTO dto) {
        JobSeekers jobSeeker = jobSeekerRepo.findById(dto.getJobSeekerId())
                .orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with ID: " + dto.getJobSeekerId()));

        

        ApplicationC app = new ApplicationC();
        app.setJobSeeker(jobSeeker);
        app.setJobListing(dto.getJobId());
        app.setAppliedDate(dto.getAppliedDate());
        app.setStatus(dto.getStatus());
        app.setResumeFilePath(dto.getResumeFilePath());

        return apprepo.save(app);
    }

    @Override
    public ApplicationC updateApplication(int appId, ApplicationDTO dto) {
        ApplicationC existingApp = apprepo.findById(appId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found with ID: " + appId));

        JobSeekers jobSeeker = jobSeekerRepo.findById(dto.getJobSeekerId())
                .orElseThrow(() -> new JobSeekerNotFoundException("JobSeeker not found with ID: " + dto.getJobSeekerId()));

       

        existingApp.setJobSeeker(jobSeeker);
        existingApp.setJobListing(dto.getJobId());
        existingApp.setAppliedDate(dto.getAppliedDate());
        existingApp.setStatus(dto.getStatus());
        existingApp.setResumeFilePath(dto.getResumeFilePath());

        return apprepo.save(existingApp);
    }

    @Override
    public ApplicationC getbyId(int appid) {
        return apprepo.findById(appid)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found for ID: " + appid));
    }

    @Override
    public String deleteByID(int appid) {
        if (!apprepo.existsById(appid)) {
            throw new ApplicationNotFoundException("Cannot delete; Application not found for ID: " + appid);
        }
        apprepo.deleteById(appid);
        return "Deleted application with ID: " + appid;
    }

    @Override
    public List<ApplicationC> getallApplication() {
        return apprepo.findAll();
    }

    

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public List<ApplicationC> getApplicationsByJobSeeker(JobSeekers seeker) {
        return apprepo.findByJobSeeker(seeker);
    }
    
    
}
