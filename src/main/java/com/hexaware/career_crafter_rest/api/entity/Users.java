package com.hexaware.career_crafter_rest.api.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userid;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    
    @Column(name = "role", nullable = false)
    @Pattern(regexp = "(?i)USER|ADMIN|EMPLOYER|JOB_SEEKER", message = "Role must be USER,ADMIN, EMPLOYER, or JOB_SEEKER")
    private String role;

    @Column(name = "is_active")
    private boolean active = true;
    

	public Users(int userid, @NotBlank String username,
			@NotBlank @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@Email @NotBlank String email,
			@Pattern(regexp = "(?i)USER|ADMIN|EMPLOYER|JOB_SEEKER", message = "Role must be USER,ADMIN, EMPLOYER, or JOB_SEEKER") String role,
			boolean active) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.active = active;
	}

	public Users() {
		super();
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
    
    
    
    
    
	


    
}

