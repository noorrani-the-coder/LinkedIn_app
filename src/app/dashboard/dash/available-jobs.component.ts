import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators ,ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Job } from '../../models/job.model';
import { Location } from '@angular/common';

import { UserService } from '../../services/user.service';
import { JobService } from '../../services/job.service';
 // adjust path

@Component({
  selector: 'app-available-jobs',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],  // ✅ FIXED
  templateUrl: './available-jobs.component.html',
  styleUrls: ['./available-jobs.component.scss']
})
export class AvailableJobsComponent implements OnInit {
  jobForm!: FormGroup;
  jobs: Job[] = [];
  editingJobId: number | null = null;
  employerId!: number;
  employer: any = null;

  constructor(
    private fb: FormBuilder,
    private jobService: JobService,
    private userService: UserService,private location: Location
  ) {}

  ngOnInit(): void {
  const userId = this.userService.getUserId(); // UserId from token
  console.log(' userId from token/localStorage:', userId);
  this.jobService.getEmployerByUserId(userId).subscribe({
  next: (employer) => {
    console.log('✅ Employer response:', employer);
    console.log('✅ Employer ID:', employer.employerId);
    this.employerId = employer.employerId;

    this.loadEmployerAndJobs();
  },
  error: (err) => {
    console.error('❌ Failed to fetch employer:', err);
  }
});
  this.initForm();
}

  initForm(): void {
    this.jobForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      location: ['', Validators.required],
      jobType: ['Full Time', Validators.required],
      qualifications: ['', Validators.required],
      postedDate: [null],
      active: [true]
    });
  }

  loadEmployerAndJobs(): void {
  this.jobService.getEmployerWithJobs(this.employerId).subscribe({
    next: (res) => {
      console.log('📦 EmployerWithJobs API response:', res);
      this.employer = res.employer || null;

      if (Array.isArray(res.jobs)) {
        this.jobs = res.jobs;
      } else if (res.jobs) {
        this.jobs = [res.jobs]; // Single object fallback
      } else {
        this.jobs = [];
      }
    },
    error: (err) => console.error('Failed to load employer/jobs:', err)
  });
}

  onSubmit(): void {
    if (this.jobForm.valid) {
      const jobData: Job = {
        ...this.jobForm.value,
        employerId: this.employerId
      };

      if (this.editingJobId) {
        this.jobService.updateJob(this.employerId, this.editingJobId, jobData).subscribe({
          next: () => {
            this.loadEmployerAndJobs();
            this.resetForm();
            alert('Job updated successfully.');
          },
          error: (err) => console.error('Update failed:', err)
        });
      } else {
        this.jobService.addJob(this.employerId, jobData).subscribe({
          next: () => {
            this.loadEmployerAndJobs();
            this.resetForm();
            alert('Job added successfully.');
          },
          error: (err) => console.error('Add failed:', err)
        });
      }
    }
  }

  editJob(job: Job): void {
    this.jobForm.patchValue(job);
    this.editingJobId = job.jobid!;
  }

  deleteJob(jobId: number): void {
    this.jobService.deleteJob(this.employerId, jobId).subscribe({
      next: () => {
        this.loadEmployerAndJobs();
        alert('Job deleted successfully.');
      },
      error: (err) => console.error('Delete failed:', err)
    });
  }

  resetForm(): void {
    this.jobForm.reset();
    this.editingJobId = null;
  }

  goBack(): void {
  this.location.back();
}
}
