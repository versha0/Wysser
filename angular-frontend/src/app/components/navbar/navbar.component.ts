import { Component, OnInit, NgZone } from '@angular/core';
import { Location } from '@angular/common';
import { RouterModule, Router, NavigationEnd } from '@angular/router';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import * as jwt_decode from 'jwt-decode';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  checkLogin;
  hideElement = false;

  constructor(private router: Router, private zone: NgZone) {
    // this.checkLogin = localStorage.getItem('token');
    // this.router.events.subscribe((event) => {
    //   if (event instanceof NavigationEnd) {
    //     if (event.url === '/login' || event.url === '/signup') {
    //       this.hideElement = true;
    //     }  else {
    //       this.hideElement = false;
    //     }
    //   }
    // });
    this.zone.run(() => {
      this.checkLogin = localStorage.getItem('token');
      this.router.events.subscribe((event) => {
        if (event instanceof NavigationEnd) {
          if (event.url === '/login' || event.url === '/signup') {
           this.hideElement = true;
          } else {
            this.hideElement = false;
            
            if(localStorage.getItem('token')==null){
              this.checkLogin = null;
            }
            else{
              this.checkLogin = 'abc';
            }

          }
        }
      });
    })
  }

  ngOnInit() {

  }


  // for clearing local storage as logout button pressed
  validateLogout() {
    console.log("loging out");

    localStorage.removeItem('token');

    this.checkLogin = null;

    

  }


  getRelatedProfile() {
    var Decoded: DecodedJwtData;
    Decoded = jwt_decode(localStorage.getItem('token'));

    if (Decoded.role === 'Retailer') {
      this.router.navigate(['/viewProfile'])
    }

    if (Decoded.role === 'VehicleCompany') {
      this.router.navigate(['/editVehicleCompanyProfile'])
    }


  }


}
