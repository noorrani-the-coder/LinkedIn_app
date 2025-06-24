import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-available-jobs',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {
  jobs: any[] = [];

  ngOnInit(): void {
    const jobsFromStorage = localStorage.getItem('postedJobs');
    this.jobs = jobsFromStorage ? JSON.parse(jobsFromStorage) : [];
  }
}
