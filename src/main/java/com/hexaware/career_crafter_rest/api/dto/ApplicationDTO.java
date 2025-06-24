package com.hexaware.career_crafter_rest.api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;




public class ApplicationDTO {
    private int jobSeekerid;
    private int jobid;
    private LocalDate appliedDate;

    @Pattern(regexp = "(?i)APPLIED|REVIEWED|SELECTED|REJECTED")
    private String status;

    private String resumeFilePath;
    

	public ApplicationDTO() {
		super();
	}

	public ApplicationDTO(int jobSeekerid, int jobid, LocalDate appliedDate,
			@Pattern(regexp = "(?i)APPLIED|REVIEWED|SELECTED|REJECTED") String status, String resumeFilePath) {
		super();
		this.jobSeekerid = jobSeekerid;
		this.jobid = jobid;
		this.appliedDate = appliedDate;
		this.status = status;
		this.resumeFilePath = resumeFilePath;
	}

	public int getJobSeekerId() {
		return jobSeekerid;
	}

	public void setJobSeekerId(int jobSeekerid) {
		this.jobSeekerid = jobSeekerid;
	}

	public int getJobId() {
		return jobid;
	}

	public void setJobId(int jobid) {
		this.jobid = jobid;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResumeFilePath() {
		return resumeFilePath;
	}

	public void setResumeFilePath(String resumeFilePath) {
		this.resumeFilePath = resumeFilePath;
	}

    // Constructors, Getters and Setters
}