package com.hexaware.career_crafter_rest.api.dto;

import java.time.LocalDate;


public class JobsDTO {
    private int jobid;
    private int employerId;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private String qualifications;
    private LocalDate postedDate;
    private boolean active;
    // Getters, Setters, Constructors...
    
    
    
	public int getJobid() {
		return jobid;
	}
	public JobsDTO() {
		super();
	}
	public JobsDTO(int jobid, int employerId, String title, String description, String location, String jobType,
			String qualifications, LocalDate postedDate, boolean active) {
		super();
		this.jobid = jobid;
		this.employerId = employerId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.jobType = jobType;
		this.qualifications = qualifications;
		this.postedDate = postedDate;
		this.active = active;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getQualifications() {
		return qualifications;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	public LocalDate getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
    
    
}
