import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.scss'],
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  errorMsg = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    this.submitted = true;
    this.errorMsg = '';

    if (this.loginForm.invalid) return;

    const { email, password } = this.loginForm.value;

    this.authService.login({ email, password }).subscribe({
      next: (res) => {
        const token = res.token;
        const role = res.user.role;
        const userId = res.user.userid; // ✅ Make sure this matches backend field name

        this.authService.saveToken(token);
        this.authService.saveRole(role);
        this.authService.saveUserId(userId);

        // Redirect based on role
        switch (role) {
          case 'JOB_SEEKER':
            this.router.navigate(['/jobseeker-form']);
            break;
          case 'ADMIN':
            this.router.navigate(['/admin-dashboard']);
            break;
          case 'EMPLOYER':
            this.router.navigate(['/employer-form']);
            break;
          default:
            this.errorMsg = 'Unknown role';
        }

        this.loginForm.reset();
        this.submitted = false;
      },
      error: () => {
        this.errorMsg = 'Invalid email or password';
      },
    });
  }
}
