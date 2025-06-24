import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-received-jobseeker-forms',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './received-jobseeker-forms.component.html',
  styleUrls: ['./received-jobseeker-forms.component.scss']
})
export class ReceivedJobseekerFormsComponent implements OnInit {
  jobSeekerData: any;

  ngOnInit(): void {
    const data = localStorage.getItem('jobSeekerData');
    this.jobSeekerData = data ? [JSON.parse(data)] : [];
  }
}
