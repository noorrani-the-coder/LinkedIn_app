package com.hexaware.career_crafter_rest.api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsersDTO {
    private int userid;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "(?i)USER|ADMIN|EMPLOYER|JOB_SEEKER")
    private String role;

    private boolean active = true;
    

    
    
	public UsersDTO() {
		super();
	}

	public UsersDTO(int userid, @NotBlank String username, @NotBlank @Size(min = 6) String password,
			@Email @NotBlank String email, @Pattern(regexp = "(?i)USER|ADMIN|EMPLOYER|JOB_SEEKER") String role,
			boolean active) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.active = active;
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
