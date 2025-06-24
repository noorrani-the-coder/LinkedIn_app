package com.hexaware.career_crafter_rest.api.service;

import java.util.List;

import com.hexaware.career_crafter_rest.api.dto.ApplicationDTO;
import com.hexaware.career_crafter_rest.api.entity.ApplicationC;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;
/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */

public interface IApplicationService {
	public ApplicationC addApplication(ApplicationDTO app);
	public ApplicationC updateApplication(int appId,ApplicationDTO app);
	
	public ApplicationC getbyId(int appid);
	public String deleteByID(int appid);
	
	public List<ApplicationC> getApplicationsByJobSeeker(JobSeekers seeker);
	public List<ApplicationC> getallApplication();
	

}
