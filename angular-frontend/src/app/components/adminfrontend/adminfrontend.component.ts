import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-adminfrontend',
  templateUrl: './adminfrontend.component.html',
  styleUrls: ['./adminfrontend.component.css']
})
export class AdminfrontendComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    // this.router.navigate(['statistics']);
  }
  goToStatistics() {
    this.router.navigate(['statistics']);
  }

  // goToDriverDetails() {
  //   this.router.navigate(['driverDetails']);
  // }

  gotToRetailerDetails() {
    this.router.navigate(['retailerDetails']);
  }

  goToVehicles() {
    this.router.navigate(['vehicleDetails']);

  }

  gotToContactUs() {
    this.router.navigate(['pendingresponses']);

  }

  gotToCareer() {
    this.router.navigate(['careerRequest']);
  }
}
