import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobService } from '../../services/job.service'; //  Adjust path as needed
import { Job } from '../../models/job.model'; //  Optional: strongly type jobs

@Component({
  selector: 'app-available-jobs',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  jobs: Job[] = []; // Optional: use your Job model

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.jobService.getAllJobs().subscribe({
      next: (response) => {
        this.jobs = response;
        console.log(' Jobs loaded:', this.jobs);
      },
      error: (err) => {
        console.error(' Failed to load jobs:', err);
      }
    });
  }
}
