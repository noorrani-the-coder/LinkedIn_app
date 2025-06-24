import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employer } from '../../models/employer.model';
import { User } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { EmployerService } from '../../services/employer.service';
import { UserService } from '../../services/user.service';
import { Location } from '@angular/common';


@Component({
  selector: 'app-employer-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employer-dashboard.component.html',
  styleUrls: ['./employer-dashboard.component.scss']
})
export class EmployerDashboardComponent implements OnInit {
  employerData: Employer | null = null;
  currentUser: User | null = null;

  constructor(
    private router: Router,
    private employerService: EmployerService,
    private userService: UserService,private location: Location
  ) {}

  ngOnInit(): void {
    const userId = this.userService.getUserId(); // from token/localStorage

    // Get user details (e.g., username)
    this.userService.getUserById(userId).subscribe({
      next: (user) => this.currentUser = user,
      error: (err) => console.error('Failed to load user', err)
    });

    // Get employer info by userId
    this.employerService.getEmployerByUserId(userId).subscribe({
      next: (employer) => {
        this.employerData = employer;
      },
      error: () => {
        this.employerData = null; // Not registered yet
      }
    });
  }

  goToEmployerRegistration(): void {
    this.router.navigate(['/employer-form']);
  }

  viewJobs(): void {
    this.router.navigate(['/available-jobs']);
  }

  viewApplications(): void {
    this.router.navigate(['/received-jobseeker-forms']);
  }

  editEmployer(): void {
  if (this.employerData?.employerId) {
    this.router.navigate(['/employer/edit', this.employerData.employerId]);
  } else {
    console.warn('Employer ID is not available for editing.');
  }
}

logout(): void {
  // Clear session/local storage or auth tokens
  localStorage.clear();

  // Navigate to login page (adjust route as needed)
  this.router.navigate(['/login']);
}
}
