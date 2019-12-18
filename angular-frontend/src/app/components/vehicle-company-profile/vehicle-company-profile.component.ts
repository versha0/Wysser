import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VechicleCompanyServiceService } from 'src/app/services/vechicle-company-service.service';
import * as jwt_decode from 'jwt-decode';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import { VehicleCompanyProfile } from 'src/app/interfaces/vehicle-company-profile';
import { LoginAuthService } from 'src/app/services/login-auth.service';

@Component({
  selector: 'app-vehicle-company-profile',
  templateUrl: './vehicle-company-profile.component.html',
  styleUrls: ['./vehicle-company-profile.component.css']
})
export class VehicleCompanyProfileComponent implements OnInit {

  constructor(private router:Router, private vehicleCompanyService:VechicleCompanyServiceService, private loginAuth:LoginAuthService ) { }


  dataFromToken:DecodedJwtData;
  vehicleCompanyData:VehicleCompanyProfile;
  cName;
  cNumber;
  cAddress;
  cGst;

  ngOnInit() {
    if(localStorage.getItem('token')!=null){
      this.dataFromToken= jwt_decode(localStorage.getItem('token'));
    }


      // getting vehicle company profile from backend if exist;

    this.vehicleCompanyService.getVehicleCompanyProfileFromEmail(this.dataFromToken.sub).subscribe( (data:any) =>{
      this.vehicleCompanyData = data;
    

      if(this.vehicleCompanyData!=null){
       this.cName = this.vehicleCompanyData.companyName;
       this.cNumber = this.vehicleCompanyData.contact;
       this.cAddress = this.vehicleCompanyData.address;
       this.cGst = this.vehicleCompanyData.gst;

      }

  
  
    })

  }


  private save(companyName,contact,address,gst){

    console.log('vehicle company data');
    console.log(companyName+'--'+contact+'-'+address+'-'+gst);


    var companyData = {
        "id": this.dataFromToken.userId,
        "companyName": companyName,
        "contact": contact,
        "address": address,
        "gst": gst,
        "email": this.dataFromToken.sub
    }

    this.vehicleCompanyService.saveVehicleCompanyProfile(companyData);

      this.router.navigate(['/vehicle-management']);
  }



  private deleteAccount(){


    this.vehicleCompanyService.deleteVehicleCompanyProfile();

    this.loginAuth.deleteCredentials();

    this.router.navigate(['/home']);

  }


}
