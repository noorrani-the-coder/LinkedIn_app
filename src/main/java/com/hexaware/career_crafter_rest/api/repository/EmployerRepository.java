package com.hexaware.career_crafter_rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career_crafter_rest.api.entity.Employer;
import java.util.Optional;
public interface EmployerRepository extends JpaRepository<Employer,Integer>{
	
	Optional<Employer> findByUser_Userid(int userid);

}
