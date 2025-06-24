package com.hexaware.career_crafter_rest.api.dto;

import java.time.LocalDate;

import com.hexaware.career_crafter_rest.api.entity.JobSeekers;

public class EnrichedApplicationDTO {
    private int appid;
    private LocalDate appliedDate;
    private String status;
    private String resumeFilePath;
    private JobsDTO job;
    private JobSeekers jobSeeker;
    
    
	public EnrichedApplicationDTO() {
		super();
	}
	public EnrichedApplicationDTO(int appid, LocalDate appliedDate, String status, String resumeFilePath, JobsDTO job,
			JobSeekers jobSeeker) {
		super();
		this.appid = appid;
		this.appliedDate = appliedDate;
		this.status = status;
		this.resumeFilePath = resumeFilePath;
		this.job = job;
		this.jobSeeker = jobSeeker;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
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
	public JobsDTO getJob() {
		return job;
	}
	public void setJob(JobsDTO job) {
		this.job = job;
	}
	public JobSeekers getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeekers jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

    // Constructor, Getters, Setters
}
