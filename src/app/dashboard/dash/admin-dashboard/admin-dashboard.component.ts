import { Component, OnInit } from '@angular/core';
 // 💡 Create this service
import { Router } from '@angular/router';
import { AdminService } from '../../../services/admin.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
  standalone: true,
  imports: [CommonModule]

})
export class AdminDashboardComponent implements OnInit {
  view: string = 'employers';
  employers: any[] = [];
  jobSeekers: any[] = [];

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.loadEmployers();
    this.loadJobSeekers();
  }

  loadEmployers(): void {
    this.adminService.getAllEmployers().subscribe({
      next: data => this.employers = data,
      error: err => console.error('Error loading employers', err)
    });
  }

  loadJobSeekers(): void {
    this.adminService.getAllJobSeekers().subscribe({
      next: data => this.jobSeekers = data,
      error: err => console.error('Error loading job seekers', err)
    });
  }

  deleteEmployer(employerId: number): void {
    if (confirm('Are you sure you want to delete this employer?')) {
      this.adminService.deleteEmployer(employerId).subscribe(() => {
        this.loadEmployers(); // refresh
      });
    }
  }

  deleteJobSeeker(jsId: number): void {
    if (confirm('Are you sure you want to delete this job seeker?')) {
      this.adminService.deleteJobSeeker(jsId).subscribe(() => {
        this.loadJobSeekers(); // refresh
      });
    }
  }

  logout(): void {
  localStorage.clear(); // or authService.logout()
  this.router.navigate(['/login']); // update the route as needed
}
}
