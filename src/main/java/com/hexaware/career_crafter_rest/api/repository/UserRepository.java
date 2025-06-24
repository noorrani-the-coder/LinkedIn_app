package com.hexaware.career_crafter_rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.career_crafter_rest.api.entity.Users;




public interface UserRepository extends JpaRepository<Users,Integer> {
	Optional<Users> findByUsername(String userName);
	Optional<Users> findByEmail(String email);

	

    boolean existsByEmail(String email);

}
