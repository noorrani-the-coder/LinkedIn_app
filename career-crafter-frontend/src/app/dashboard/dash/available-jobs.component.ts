import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-available-jobs',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './available-jobs.component.html',
  styleUrls: ['./available-jobs.component.scss']
})
export class AvailableJobsComponent implements OnInit {
  jobForm!: FormGroup;
  jobs: any[] = [];
  editingIndex: number | null = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    // Load existing jobs from localStorage
    const jobsFromStorage = localStorage.getItem('postedJobs');
    this.jobs = jobsFromStorage ? JSON.parse(jobsFromStorage) : [];

    // Initialize form
    this.jobForm = this.fb.group({
      jobTitle: ['', Validators.required],
      jobDescription: ['', Validators.required],
      location: ['', Validators.required],
      salary: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.jobForm.valid) {
      if (this.editingIndex !== null) {
        // Update job
        this.jobs[this.editingIndex] = this.jobForm.value;
        this.editingIndex = null;
      } else {
        // Add new job
        this.jobs.push(this.jobForm.value);
      }

      // Save updated jobs to localStorage
      localStorage.setItem('postedJobs', JSON.stringify(this.jobs));

      // Reset form
      this.jobForm.reset();
    }
  }

  editJob(index: number): void {
    this.jobForm.setValue(this.jobs[index]);
    this.editingIndex = index;
  }

  deleteJob(index: number): void {
    this.jobs.splice(index, 1);

    // Save updated jobs to localStorage
    localStorage.setItem('postedJobs', JSON.stringify(this.jobs));

    // Reset form if editing deleted job
    if (this.editingIndex === index) {
      this.jobForm.reset();
      this.editingIndex = null;
    }
  }
}