package com.hexaware.career_crafter_rest.api.dto;




public class EmployerDTO {
    private int employerid;
    private int userid;
    private String companyName;
    private String industry;
    private String contactPerson;
    private String contactEmail;
    
	public int getEmployerId() {
		return employerid;
	}
	public void setEmployerId(int employerid) {
		this.employerid = employerid;
	}
	public int getUserId() {
		return userid;
	}
	public void setUserId(int userid) {
		this.userid = userid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public EmployerDTO(int employerid, int userid, String companyName, String industry, String contactPerson,
			String contactEmail) {
		super();
		this.employerid = employerid;
		this.userid = userid;
		this.companyName = companyName;
		this.industry = industry;
		this.contactPerson = contactPerson;
		this.contactEmail = contactEmail;
	}
	public EmployerDTO() {
		super();
	}
    
    
}
