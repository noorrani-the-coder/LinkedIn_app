import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employer-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employer.html',
  styleUrls: ['./employer.scss']
})
export class EmployerFormComponent implements OnInit {
  employerForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.employerForm = this.fb.group({
      companyName: ['', Validators.required],
      industry: [''],
      contactPerson: [''],
      contactEmail: ['', [Validators.required, Validators.email]],
      // userId should ideally come from the AuthService after login
    });
  }

  onSubmit(): void {
  if (this.employerForm.valid) {
    const employerData = this.employerForm.value;
    console.log('Submitted Employer Data:', employerData);

    // Store data in localStorage
    localStorage.setItem('employerData', JSON.stringify(employerData));

    // Navigate to dashboard
    this.router.navigate(['/employer-dashboard']);
  } else {
    console.log('Form is invalid');
  }
}
}
