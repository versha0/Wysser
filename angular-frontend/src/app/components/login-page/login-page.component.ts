import { Component, OnInit } from '@angular/core';
import { LoginAuthService } from 'src/app/services/login-auth.service';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import { EditProfileService } from 'src/app/services/edit-profile.service';
import { VechicleCompanyServiceService } from 'src/app/services/vechicle-company-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  jwtTokenObj = {
    "token": ""
  }

  token;


  constructor(private loginAuthService: LoginAuthService, 
    private router: Router, 
    private editProfileService: EditProfileService, 
    private vehicleCompanyProfile: VechicleCompanyServiceService,

    ) {
  }

  ngOnInit() {
  }


  loginMessage = '';
  decodedDetail: DecodedJwtData;

  validateLogin(email, password) {

    var loginData = {
      "email": email,
      "password": password
    }

    // getting jwtToken from backend and storing it in localstorage

    this.loginAuthService.getToken(loginData).subscribe((datas: any) => {
      this.jwtTokenObj = datas;

      if (this.jwtTokenObj != null) {
        this.token = this.jwtTokenObj.token;  //getting token string from json if it's not NULL

        localStorage.setItem('token', this.token);  // soring token in local storage

        console.log(this.token);


        this.decodedDetail = jwt_decode(this.token);   // decoding token into json objects
        console.log(this.decodedDetail);
        console.log(this.decodedDetail.sub);

        //////////////////////////////////////////////////////////////////////////////////////////////

        if (this.decodedDetail.role === "Retailer") {
          // For user who has not updated his proile
          this.editProfileService.getProfileFromEmail(this.decodedDetail.sub).subscribe((datas: any) => {
            if ((datas != null))
              this.router.navigate(['/user']);
            else
              this.router.navigate(['/editProfile']);

          });
        }

        if (this.decodedDetail.role === "VehicleCompany") {

          this.vehicleCompanyProfile.getVehicleCompanyProfileFromEmail(this.decodedDetail.sub).subscribe((datas: any) => {

            if ((datas != null))
              this.router.navigate(['/vehicle-management']);
            else
              this.router.navigate(['/editVehicleCompanyProfile']);
          });
        }

      }
      else {
        this.loginMessage = 'UserName or Password is incorrect';
      }


    });

  }


}
