package com.hexaware.career_crafter_rest.api.dto;

import com.hexaware.career_crafter_rest.api.entity.Employer;
import java.util.List;
public class EmployerJobVO {

    private Employer employer;
    private List<JobsDTO> jobs; // ✅ List of jobs

    public EmployerJobVO() {}

    public EmployerJobVO(Employer employer, List<JobsDTO> jobs) {
        this.employer = employer;
        this.jobs = jobs;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<JobsDTO> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsDTO> jobs) {
        this.jobs = jobs;
    }
}

