import { Component } from '@angular/core';

@Component({
  selector: 'app-received-employer',
  imports: [],
  templateUrl: './received-employer.html',
  styleUrl: './received-employer.scss'
})
export class ReceivedEmployer {

   employerData: any;
  ngOnInit(): void {
    const data = localStorage.getItem('employerData');
    this.employerData = data ? JSON.parse(data) : null;
  }

}
