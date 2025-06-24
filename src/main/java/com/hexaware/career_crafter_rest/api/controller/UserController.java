package com.hexaware.career_crafter_rest.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.career_crafter_rest.api.config.UserInfoUserDetails;

import com.hexaware.career_crafter_rest.api.dto.AuthResponseDTO;
import com.hexaware.career_crafter_rest.api.dto.LoginRequestDTO;
import com.hexaware.career_crafter_rest.api.dto.MessageResponse;
import com.hexaware.career_crafter_rest.api.dto.UsersDTO;
import com.hexaware.career_crafter_rest.api.entity.Users;
import com.hexaware.career_crafter_rest.api.service.IUsers;



import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUsers userService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody @Valid UsersDTO userDTO) {
        log.info("User registration requested for email: {}", userDTO.getEmail());
        String response = userService.registerUser(userDTO);
        log.info("Registration completed for email: {}", userDTO.getEmail());
        return ResponseEntity.ok(new MessageResponse("Registration successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginDTO) {
        log.info("Login attempt for email: {}", loginDTO.getEmail());
        AuthResponseDTO response = userService.loginUser(loginDTO);
        log.info("Login successful for email: {}", loginDTO.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Users>> getAllUsers() {
        log.info("ADMIN requested all users");
        List<Users> users = userService.getallUsers();
        log.info("Total users fetched: {}", users.size());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        log.info("Fetching user with ID: {}", userId);
        Users user = userService.getbyId(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.userId")
    public ResponseEntity<Users> updateUser(@PathVariable int userId, @RequestBody UsersDTO userDTO) {
        log.info("User update requested for ID: {}", userId);
        Users updatedUser = userService.updateUsers(userId, userDTO);
        log.info("User updated successfully: {}", updatedUser.getUserid());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<String> deleteUser(@PathVariable int userId, Authentication authentication) {
        String loggedInUserEmail = authentication.getName();
        Users loggedInUser = userService.findByEmail(loggedInUserEmail);

        if (loggedInUser.getRole().equals("ADMIN")) {
            log.info("ADMIN deleting user with ID: {}", userId);
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully by admin");
        }

        if (loggedInUser.getRole().equals("USER")) {
            if (loggedInUser.getUserid() != userId) {
                log.warn("User ID: {} attempted to delete another user's account: {}", loggedInUser.getUserid(), userId);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can delete only your own account");
            }
            log.info("User deleting own account with ID: {}", userId);
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully");
        }

        log.warn("Unauthorized delete attempt by role: {}", loggedInUser.getRole());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete users");
    }
}