package com.hexaware.career_crafter_rest.api.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.stereotype.Service;

import com.hexaware.career_crafter_rest.api.dto.AuthResponseDTO;
import com.hexaware.career_crafter_rest.api.dto.LoginRequestDTO;
import com.hexaware.career_crafter_rest.api.dto.UsersDTO;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.repository.UserRepository;


import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexaware.career_crafter_rest.api.exception.UserNotFoundException;

/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */

@Slf4j
@Service
public class UserServiceImpl implements IUsers {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository urepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Override
    public String addUsers(UsersDTO userdto) {
        log.info("Registering new user with email: {}", userdto.getEmail());

        if (urepo.existsByEmail(userdto.getEmail())) {
            log.warn("Attempt to register with existing email: {}", userdto.getEmail());
            throw new RuntimeException("Email already exists");
        }

        Users user = new Users();
        user.setUsername(userdto.getUsername());
        user.setEmail(userdto.getEmail());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setRole(userdto.getRole());
        user.setActive(userdto.isActive());

        urepo.save(user);
        log.info("User registered successfully with email: {}", userdto.getEmail());

        return "User added successfully" ;
    }

    @Override
    public Users updateUsers(int userId, UsersDTO userDto) {
        log.info("Updating user with ID: {}", userId);

        Optional<Users> optionalUser = urepo.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setActive(userDto.isActive());
            user.setRole(userDto.getRole());
            

            log.info("User with ID: {} updated successfully", userId);
            return urepo.save(user);
        } else {
            log.error("User not found with ID: {}", userId);
            throw new UserNotFoundException("User not found with email: " + userDto.getEmail());
        }
    }

    @Override
    public Users getbyId(int userId) {
        log.debug("Fetching user by ID: {}", userId);
        return urepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }


    @Override
    public ResponseEntity<String> deleteUserById(int userId) {
        log.info("Deleting user with ID: {}", userId);

        if (!urepo.existsById(userId)) {
            log.warn("User with ID {} not found for deletion", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found.");
        }

        urepo.deleteById(userId);
        log.info("User with ID {} deleted successfully", userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully.");
    }

    @Override
    public List<Users> getallUsers() {
        log.info("Fetching all users");
        return urepo.findAll();
    }

    @Override
    public Users findByEmail(String email) {
        return urepo.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }
    
    public String registerUser(UsersDTO userDto) {
        if (urepo.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        user.setActive(userDto.isActive());

        urepo.save(user);
        return "User registered successfully";
    }

    public AuthResponseDTO loginUser(LoginRequestDTO loginDto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
        	Users user = urepo.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        	String token = jwtService.generateToken(user);
            return new AuthResponseDTO(token, user); // ✅ Return both token and user
        } else {
            throw new RuntimeException("Invalid login");
        }
    }
}
