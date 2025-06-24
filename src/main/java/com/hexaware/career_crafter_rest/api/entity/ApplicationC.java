package com.hexaware.career_crafter_rest.api.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "applications")
public class ApplicationC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int appid;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeekers jobSeeker;

    
    @Column(name = "job_id", nullable = false)
    private int jobListing;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    
    @Column(name = "status")
    @Pattern(regexp = "(?i)APPLIED|REVIEWED|SELECTED|REJECTED", message = "Status must be PPLIED, REVIEWED, SELECTED, REJECTED")
    private String status;

    @Column(name = "resume_file_path")
    private String resumeFilePath;
    
    

	public ApplicationC() {
		super();
	}



	public int getAppid() {
		return appid;
	}



	public void setAppid(int appid) {
		this.appid = appid;
	}



	public JobSeekers getJobSeeker() {
		return jobSeeker;
	}



	public void setJobSeeker(JobSeekers jobSeeker) {
		this.jobSeeker = jobSeeker;
	}



	public int getJobListing() {
		return jobListing;
	}



	public void setJobListing(int jobListing) {
		this.jobListing = jobListing;
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

	
    
}

