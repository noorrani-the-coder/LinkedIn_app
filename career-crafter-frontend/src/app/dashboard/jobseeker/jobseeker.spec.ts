import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-jobseeker-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './jobseeker-form.component.html',
  styleUrls: ['./jobseeker-form.component.scss']
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

  // ✅ Add this method inside the class
  onSubmit(): void {
    console.log('Submit clicked', this.jobSeekerForm.value);  // See output in browser console

    if (this.jobSeekerForm.valid) {
      const formData = this.jobSeekerForm.value;
      localStorage.setItem('jobSeekerData', JSON.stringify(formData));
      this.router.navigate(['/jobseeker-dashboard']);
    }
  }
}
