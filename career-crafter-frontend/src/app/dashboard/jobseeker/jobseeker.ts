import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-jobseeker-form',
  standalone: true,
  templateUrl: './jobseeker.html',
  styleUrls: ['./jobseeker.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class JobseekerFormComponent implements OnInit {
  jobSeekerForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.jobSeekerForm = this.fb.group({
      fullName: ['', Validators.required],
      education: ['', Validators.required],
      experience: ['', Validators.required],
      skills: ['', Validators.required],
      contactInfo: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    console.log('Form submitted'); // ✅ Check this in console
    if (this.jobSeekerForm.valid) {
      const formData = this.jobSeekerForm.value;
      localStorage.setItem('jobSeekerData', JSON.stringify(formData));
      this.router.navigate(['/jobseeker-dashboard']);
    } else {
      console.warn('Form is invalid'); // ✅ Debug info
    }
  }
}
