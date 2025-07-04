package com.hexaware.career_crafter_rest.api.dto;

public class AuthRequest { // pojo or DTO

	private String username;
	private String password;

	public AuthRequest() {
		super();
	}

	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

}
