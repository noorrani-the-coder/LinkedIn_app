package com.hexaware.career_crafter_rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career_crafter_rest.api.entity.JobSeekers;



public interface JobSeekerRepository extends JpaRepository<JobSeekers,Integer>{
	
	Optional<JobSeekers> findByUserUserid(int userid);
}
