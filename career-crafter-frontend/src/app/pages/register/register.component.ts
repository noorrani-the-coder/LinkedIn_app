import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-register',
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],  // ✅ changed from 'name' to 'username'
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['', Validators.required],
      active: [true] // ✅ optional: matches backend field
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) return;

    this.http.post('http://13.60.83.98:8080/api/users/register', this.registerForm.value, { responseType: 'text' })
      .subscribe({
        next: (res) => {
          alert(res);  // "User registered successfully"
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error('Registration failed:', err);
          alert('Registration failed');
        }
      });
  }
}
