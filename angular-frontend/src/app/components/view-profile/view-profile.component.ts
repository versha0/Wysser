import { Component, OnInit } from '@angular/core';
import { EditProfileService } from '../../services/edit-profile.service';
import * as jwt_decode from 'jwt-decode';
import { Retailerdetails } from 'src/app/interfaces/retailerDetails';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import { Router } from '@angular/router';
import { LoginAuthService } from 'src/app/services/login-auth.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {


  companyName;



  constructor(private editProfileService: EditProfileService, private router: Router, private loginAuth:LoginAuthService) {
  }

  decodedData: DecodedJwtData;
  retailerObj: Retailerdetails;
  picurl = '';
  docurl = '';
  check = '';


  ngOnInit() {


    this.companyName = "";

    let token = localStorage.getItem('token');
    let email = '';

    if (token != null) {
      this.decodedData = jwt_decode(token);
      email = this.decodedData.sub;
    }


    // finding profile detail from email

    this.editProfileService.getProfileFromEmail(email).subscribe((datas: any) => {
      this.retailerObj = datas;
      this.picurl = 'data:' + this.retailerObj.profilePicType + ';base64,' + this.retailerObj.profilePic;
      this.docurl = 'data:' + this.retailerObj.docPicType + ';base64,' + this.retailerObj.docPic;
      this.check = '.'

      if(this.retailerObj != null){
        this.companyName = this.retailerObj.companyName; 
      }
    });
  }

  deleteAccount(){
    this.editProfileService.deleteRetailerAccount();
    this.loginAuth.deleteCredentials();
    this.router.navigate(['/home']);
    alert('Your account has heen deleted');
  }



}
