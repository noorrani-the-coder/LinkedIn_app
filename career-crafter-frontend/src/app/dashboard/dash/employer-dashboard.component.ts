import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employer-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employer-dashboard.component.html',
  styleUrls: ['./employer-dashboard.component.scss']
})
export class EmployerDashboardComponent implements OnInit {
  employerData: any;

  constructor(private router: Router) {}

  ngOnInit(): void {
    const data = localStorage.getItem('employerData');
    if (data) {
      this.employerData = JSON.parse(data);
    }
  }

  viewJobs(): void {
    this.router.navigate(['/available-jobs']);
  }

  viewApplications(): void {
    this.router.navigate(['/received-jobseeker-forms']);
  }
}
