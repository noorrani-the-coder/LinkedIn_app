package com.hexaware.career_crafter_rest.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hexaware.career_crafter_rest.api.dto.AuthResponseDTO;
import com.hexaware.career_crafter_rest.api.dto.LoginRequestDTO;
import com.hexaware.career_crafter_rest.api.dto.UsersDTO;
import com.hexaware.career_crafter_rest.api.entity.Users;


import jakarta.validation.Valid;

/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */

public interface IUsers {
	public Users findByEmail(String email);
	public ResponseEntity<String> deleteUserById(int userId);
	public Users getbyId(int userId);
	public Users updateUsers(int userId, UsersDTO userDto);
	public String addUsers(UsersDTO userDto);
	public List<Users> getallUsers();
	String registerUser(@Valid UsersDTO userDTO);
	AuthResponseDTO loginUser(LoginRequestDTO loginDTO);

}
