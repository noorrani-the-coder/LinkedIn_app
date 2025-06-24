package com.hexaware.career_crafter_rest.api.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.dto.JobSeekerDTO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;



public interface IJobSeekerService {
	public JobSeekers addJobSeeker(JobSeekerDTO jsee);
	public JobSeekers updateJobSeeker(int jsid,JobSeekerDTO jsee);
	
	public JobSeekers getJobSeekerById(int jsid);
	public String deleteJobSeekerById(int jsid);
	public String applyForJob(ApplicationDTO dto);
	public List<JobSeekers> getAllJobSeekers();
	public List<JobsDTO> getAllJobs();
	


}
