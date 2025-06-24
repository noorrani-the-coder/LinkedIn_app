import { Component, OnInit } from '@angular/core';
import { JobSeekerService } from '../../services/jobseeker.service';
import { JobService } from '../../services/job.service';
import { ApplicationService } from '../../services/application.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { of,forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-jobseeker-dashboard',
  templateUrl: './jobseeker-dashboard.component.html',
  styleUrls: ['./jobseeker-dashboard.component.scss'],
  standalone: true,
  imports: [CommonModule]
})
export class JobseekerDashboardComponent implements OnInit {
  view: string = 'profile';
  jobSeekerData: any;
  jobs: any[] = [];
  appliedJobs: any[] = [];

  constructor(
    private jobSeekerService: JobSeekerService,
    private jobService: JobService,
    private applicationService: ApplicationService,
    private userService: UserService,
    private router: Router
  ) {}





ngOnInit(): void {
  const userId = this.userService.getUserId();

  this.jobSeekerService.getJobSeekerByUserId(userId).subscribe({
    next: (data) => {
      this.jobSeekerData = data;

      if (data.jsId !== undefined) {
        this.applicationService.getApplicationsByJobSeekerId(data.jsId).subscribe({
          next: (apps) => {
            const jobFetches = apps
              .filter(app => app.jobListing != null)
              .map(app =>
                this.jobService.getJobById(app.jobListing).pipe(
                  map(job => ({
                    ...app,
                    job // attach job under key `job`
                  }))
                )
              );

            if (jobFetches.length === 0) {
              this.appliedJobs = [];
              return;
            }

            forkJoin(jobFetches).subscribe({
              next: (appsWithJobs) => {
                this.appliedJobs = appsWithJobs;
              },
              error: (err) => {
                console.error('❌ Error enriching apps with jobs:', err);
                this.appliedJobs = [];
              }
            });
          },
          error: () => {
            this.appliedJobs = [];
          }
        });
      }
    },
    error: () => {
      this.jobSeekerData = null;
    }
  });

  this.jobService.getAllJobs().subscribe({
    next: (data) => (this.jobs = data),
    error: () => (this.jobs = [])
  });
}





  goToRegister(): void {
    this.router.navigate(['/jobseeker-form']);
  }

  applyToJob(jobId: number): void {
    console.log("job is: "+ jobId);
    const seekerId = this.jobSeekerData?.jsId;
    console.log("Jobseeker id:"+ seekerId);
    if (!seekerId) {
      alert('You must register as a JobSeeker before applying.');
      return;
    }

    const resumePath = `${this.jobSeekerData.fullName.replace(/\s+/g, '_').toLowerCase()}_resume.pdf`;

    const applicationData = {
      jobSeekerId: seekerId,
      jobId: jobId,
      resumeFilePath: resumePath
    };

    this.applicationService.apply(applicationData).subscribe({
      next: () => {
        alert(' Applied successfully!');
        this.ngOnInit(); // Refresh applied jobs
        this.view = 'applied';
      },
      error: () => alert('Applied successfully!')
    });
  }

  editJobSeeker(): void {
  if (this.jobSeekerData?.jsId) {
    this.router.navigate(['/jobseeker/edit', this.jobSeekerData.jsId]);
  } else {
    console.warn('JobSeeker ID is not available for editing.');
  }
}

logout(): void {
  localStorage.clear(); // or authService.logout()
  this.router.navigate(['/login']); // update the route as needed
}


}
