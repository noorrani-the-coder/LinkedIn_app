package com.hexaware.career_crafter_rest.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.career_crafter_rest.api.dto.EmployerJobVO;
import com.hexaware.career_crafter_rest.api.dto.EmployerDTO;
import com.hexaware.career_crafter_rest.api.dto.JobsDTO;
import com.hexaware.career_crafter_rest.api.entity.Employer;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.exception.EmployerNotFoundException;
import com.hexaware.career_crafter_rest.api.exception.JobSeekerNotFoundException;
import com.hexaware.career_crafter_rest.api.exception.UserNotFoundException;
import com.hexaware.career_crafter_rest.api.repository.EmployerRepository;
import com.hexaware.career_crafter_rest.api.repository.UserRepository;
/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */


@Service
public class EmployerServiceImpl implements IEmployerService {

    @Autowired
    private EmployerRepository employerRepo;
    
    @Autowired
    private RestTemplate restTemplate;




    @Autowired
    private UserRepository usersRepo;

    @Override
    public Employer addEmployer(EmployerDTO dto) {
        Users user = usersRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUserId()));

        Employer employer = new Employer();
        employer.setUser(user);
        employer.setCompanyName(dto.getCompanyName());
        employer.setIndustry(dto.getIndustry());
        employer.setContactPerson(dto.getContactPerson());
        employer.setContactEmail(dto.getContactEmail());

        return employerRepo.save(employer);
    }

    @Override
    public Employer updateEmployer(int id, EmployerDTO dto) {
        Employer employer = employerRepo.findById(id)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with ID: " + id));

        Users user = usersRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUserId()));

        employer.setUser(user);
        employer.setCompanyName(dto.getCompanyName());
        employer.setIndustry(dto.getIndustry());
        employer.setContactPerson(dto.getContactPerson());
        employer.setContactEmail(dto.getContactEmail());

        return employerRepo.save(employer);
    }

    @Override
    public Employer getEmployerById(int id) {
        return employerRepo.findById(id)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with ID: " + id));
    }

    @Override
    public String deleteEmployerById(int id) {
        if (!employerRepo.existsById(id)) {
            throw new EmployerNotFoundException("Employer not found with ID: " + id);
        }
        employerRepo.deleteById(id);
        return "Deleted employer with ID: " + id;
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepo.findAll();
    }
    
  

   

    @Override
    public JobsDTO updateJobForEmployer(int jobId, JobsDTO jobDto) {
        String url = "http://localhost:8082/api/jobs/updatejobs/" + jobId;
        restTemplate.put(url, jobDto);  
        return jobDto;  
    }

    @Override
    public String deleteJobForEmployer(int jobId) {
        String url = "http://localhost:8082/api/jobs/deletebyid/" + jobId;
        restTemplate.delete(url);
        return "Job with ID " + jobId + " deleted successfully";
    }

    @Override
    public EmployerJobVO getEmployerAndJob(int employerId) {
        Employer employer = getEmployerById(employerId); 

        String url = "http://localhost:8082/api/jobs/getJobEmpId/" + employerId;

        ResponseEntity<List<JobsDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<JobsDTO>>() {}
        );
        List<JobsDTO> jobs = response.getBody();

        return new EmployerJobVO(employer, jobs);
    }


    
    @Override
    public JobsDTO addJobForEmployer(JobsDTO jobDto) {
        return restTemplate.postForObject("http://localhost:8082/api/jobs/addjobs", jobDto, JobsDTO.class);
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
    
    
	public Employer getEmployerByUserId(int userid){
    	
    	return employerRepo.findByUser_Userid(userid).orElseThrow(() -> new EmployerNotFoundException("Employer not found with ID: " + userid));
    	
    }

	


}
