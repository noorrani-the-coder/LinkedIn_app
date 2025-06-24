package com.hexaware.career_crafter_rest.api.dto;



public class JobSeekerDTO {
    private int jsid;
    private int userid;
    private String fullName;
    private String education;
    private String experience;
    private String skills;
    private String contactInfo;
    
    
	public JobSeekerDTO() {
		super();
	}


	public JobSeekerDTO(int jsid, int userId, String fullName, String education, String experience, String skills,
			String contactInfo) {
		super();
		this.jsid = jsid;
		this.userid = userId;
		this.fullName = fullName;
		this.education = education;
		this.experience = experience;
		this.skills = skills;
		this.contactInfo = contactInfo;
	}


	public int getJsid() {
		return jsid;
	}


	public void setJsid(int jsid) {
		this.jsid = jsid;
	}


	public int getUserId() {
		return userid;
	}


	public void setUserId(int userId) {
		this.userid = userId;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}


	public String getContactInfo() {
		return contactInfo;
	}


	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
    
    // Getters, Setters, Constructors...
}
