import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { JobseekerFormComponent } from './dashboard/jobseeker/jobseeker';
import { JobseekerDashboardComponent } from './dashboard/dash/jobseeker-dashboard.component';
import { EmployerFormComponent } from './dashboard/employer/employer';
import { EmployerDashboardComponent } from './dashboard/dash/employer-dashboard.component';
import { AvailableJobsComponent } from './dashboard/dash/available-jobs.component';
import { ReceivedJobseekerFormsComponent } from './dashboard/dash/received-jobseeker-forms.component';
import { JobsComponent } from './dashboard/dash/jobs.component';
import { EditEmployerComponent } from './dashboard/components/Employer/edit-employer/edit-employer.component';
import { RouterModule } from '@angular/router';
import { AdminDashboardComponent } from './dashboard/dash/admin-dashboard/admin-dashboard.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' }, // 👈 Default now goes to Home
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'jobseeker-form', component: JobseekerFormComponent},
  { path: 'jobseeker-dashboard', component:  JobseekerDashboardComponent },
  { path: 'employer-form', component: EmployerFormComponent },
  { path: 'employer-dashboard', component: EmployerDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'available-jobs', component: AvailableJobsComponent },
 {path: 'jobs',component: JobsComponent},
  {path: 'employer/edit/:id',component:EditEmployerComponent},
  {path: 'received-jobseeker-forms',component: ReceivedJobseekerFormsComponent}

];

