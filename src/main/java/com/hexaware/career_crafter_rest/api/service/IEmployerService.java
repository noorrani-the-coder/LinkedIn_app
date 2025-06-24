package com.hexaware.career_crafter_rest.api.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.career_crafter_rest.api.dto.EmployerDTO;
import com.hexaware.career_crafter_rest.api.dto.EmployerJobVO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.Employer;


public interface IEmployerService {
	
	
	public Employer addEmployer(EmployerDTO emp);
	public Employer updateEmployer(int id,EmployerDTO emp);
	
	public Employer getEmployerById(int eid);
	public String deleteEmployerById(int eid);
	
	
	public List<Employer> getAllEmployers();
	
	
	
	public JobsDTO updateJobForEmployer(int jobId, JobsDTO jobDto);
	
	public String deleteJobForEmployer(int jobId);
	
	public EmployerJobVO getEmployerAndJob(int employerId);
	
	public JobsDTO addJobForEmployer(JobsDTO jobDto);
	
	public List<JobsDTO> getAllJobs();
	


}
