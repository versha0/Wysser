import { Component, OnInit } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import { VehicleCompanyProfile } from 'src/app/interfaces/vehicle-company-profile';
import { VechicleCompanyServiceService } from 'src/app/services/vechicle-company-service.service';
@Component({
  selector: 'app-vehicle-management',
  templateUrl: './vehicle-management.component.html',
  styleUrls: ['./vehicle-management.component.css']
})
export class VehicleManagementComponent implements OnInit {


  dataFromToken: DecodedJwtData;
  vehicleCompanyData: VehicleCompanyProfile;
  cName='';

  constructor(private vehicleCompanyService: VechicleCompanyServiceService) {
  }

  ngOnInit() {

    if(localStorage.getItem('token')!=null){
      this.dataFromToken= jwt_decode(localStorage.getItem('token'));
    }


    this.vehicleCompanyService.getVehicleCompanyProfileFromEmail(this.dataFromToken.sub).subscribe((data: any) => {
      this.vehicleCompanyData = data;
      if (this.vehicleCompanyData != null) {
        this.cName = "Company Name : " + this.vehicleCompanyData.companyName;
      }
    })

   }

}
