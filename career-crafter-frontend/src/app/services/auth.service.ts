import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://13.60.83.98:8080/api/users';
  private tokenKey = 'token';
  private roleKey = 'role';
  private userIdKey = 'userId';

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials);
  }

  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  saveRole(role: string): void {
    localStorage.setItem(this.roleKey, role);
  }

  saveUserId(userId: number): void {
    localStorage.setItem(this.userIdKey, userId.toString());
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getRole(): string | null {
    return localStorage.getItem(this.roleKey);
  }

  getUserId(): string | null {
    return localStorage.getItem(this.userIdKey);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.roleKey);
    localStorage.removeItem(this.userIdKey);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  isJobSeeker(): boolean {
    return this.getRole() === 'JOB_SEEKER';
  }

  isAdmin(): boolean {
    return this.getRole() === 'ADMIN';
  }

  isEmployer(): boolean {
    return this.getRole() === 'EMPLOYER';
  }
}
