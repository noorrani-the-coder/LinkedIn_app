package com.hexaware.career_crafter_rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hexaware.career_crafter_rest.api.entity.ApplicationC;
import com.hexaware.career_crafter_rest.api.entity.JobSeekers;

public interface ApplicationRepository extends JpaRepository<ApplicationC,Integer>{
	

	List<ApplicationC> findByJobSeeker(JobSeekers seeker);

}
