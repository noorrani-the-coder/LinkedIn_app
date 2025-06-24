import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'; // ✅ Add this

@Component({
  standalone: true,
  imports: [CommonModule],
  selector: 'app-jobseeker-dashboard',
  templateUrl: './jobseeker-dashboard.component.html',
  styleUrls: ['./jobseeker-dashboard.component.scss']
})
export class JobseekerDashboardComponent implements OnInit {
  jobSeekerData: any;

  constructor(private router: Router) {}

  ngOnInit(): void {
    const data = localStorage.getItem('jobSeekerData');
    this.jobSeekerData = data ? JSON.parse(data) : null;
  }

  // ✅ Navigate to available jobs page
  goToAvailableJobs(): void {
    this.router.navigate(['/jobs']);
  }
}
