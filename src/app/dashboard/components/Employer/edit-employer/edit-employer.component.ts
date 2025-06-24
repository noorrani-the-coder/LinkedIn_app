
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Component, OnInit } from '@angular/core';
import { EmployerService } from '../../../../services/employer.service';
import { Employer } from '../../../../models/employer.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-edit-employer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './edit-employer.component.html',
  styleUrls: ['./edit-employer.component.scss']
})
export class EditEmployerComponent implements OnInit {
  employerForm!: FormGroup;
  employerId!: number;
  userId!: number; // ✅ Store userId separately

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private employerService: EmployerService
  ) {}

  ngOnInit(): void {
    this.employerId = Number(this.route.snapshot.paramMap.get('id'));

    this.employerForm = this.fb.group({
      companyName: ['', Validators.required],
      industry: [''],
      contactPerson: [''],
      contactEmail: ['', [Validators.required, Validators.email]]
    });

    this.employerService.getEmployerById(this.employerId).subscribe({
      next: (data: any) => {
        this.employerForm.patchValue(data);
        this.userId = data.user?.userid || data.userId; // ✅ Capture userId
      },
      error: (err) => console.error('Failed to load employer:', err)
    });
  }

  goToDashboard(): void {
    this.router.navigate(['/employer-dashboard']);
  }

  onSubmit(): void {
    if (this.employerForm.valid) {
      const updatedData: Employer = {
        ...this.employerForm.value,
        userId: this.userId, // ✅ Include userId in update
        employerId: this.employerId
      };

      this.employerService.updateEmployer(this.employerId, updatedData).subscribe({
        next: () => {
          alert('Employer profile updated successfully!');
          this.router.navigate(['/employer-dashboard']);
        },
        error: (err) => {
          console.error('Update failed:', err);
          alert('Failed to update employer profile.');
        }
      });
    }
  }
}


