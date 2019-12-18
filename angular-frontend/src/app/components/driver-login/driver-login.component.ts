import { Driver } from './../../interfaces/driver';
import { DriverDashboardService } from './../../services/driver-dashboard.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-driver-login',
  templateUrl: './driver-login.component.html',
  styleUrls: ['./driver-login.component.css']
})
export class DriverLoginComponent implements OnInit {

  constructor(private router: Router, private serv: DriverDashboardService) { }

  ngOnInit() {
  }

  username: any;
  password: any;
  driver: Driver;

  checkLogin(){
    // //check login details with vehicle company
    // if(this.username == "1" && this.password == "abcde"){
    //   localStorage.setItem("vehicleId",this.username);
    //   this.router.navigateByUrl('driverdashboard');
    // } else {
    //   alert('please enter correct username and password');
    // }
    this.serv.authenticateDriver(this.username, this.password).subscribe(data => {
      if(data[0].password == this.password){
        localStorage.removeItem("route");
        localStorage.removeItem("depotAddress");
        localStorage.setItem("driver", "true");
        localStorage.setItem("vehicleNumber", data[0].vehicleNumber);
        localStorage.setItem("retailerId", String(data[0].retailerId));
        localStorage.setItem("driverName", data[0].driverName);
        this.router.navigateByUrl('driverdashboard');
      }else {
        alert("Please enter correct credentials!");
      }
    });
  }
}
