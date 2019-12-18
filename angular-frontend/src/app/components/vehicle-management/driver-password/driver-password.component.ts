import { Component, OnInit, NgZone } from '@angular/core';
import { RequestService } from 'src/app/services/request.service';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import * as jwt_decode from 'jwt-decode';
import { VechicleCompanyServiceService } from 'src/app/services/vechicle-company-service.service';

@Component({
  selector: 'app-driver-password',
  templateUrl: './driver-password.component.html',
  styleUrls: ['./driver-password.component.css']
})
export class DriverPasswordComponent implements OnInit {
  Vehicles: Array<any>;
  
  dataFromToken:DecodedJwtData;
  cName;
  vehicleCompanyData;

  constructor(private requestService: RequestService, private zone:NgZone, private vehicleCompanyService: VechicleCompanyServiceService) { }

  ngOnInit() {

    if(localStorage.getItem('token')!=null){
      this.dataFromToken= jwt_decode(localStorage.getItem('token'));
    }
    
    this.vehicleCompanyService.getVehicleCompanyProfileFromEmail(this.dataFromToken.sub).subscribe((data: any) => {
      this.vehicleCompanyData = data;
      if (this.vehicleCompanyData != null) {
        this.cName = ''+this.vehicleCompanyData.companyName;
        this.requestService.getAcceptedVehicle(this.cName).subscribe( (data:any) => {
          this.zone.run(()=>{
            this.Vehicles = data
            // console.log('yyyyyyy');
            // console.log(data);
    
          })
        
      });
      }
    })
    
  //   this.requestService.getAcceptedVehicle(this.cName).subscribe( (data:any) => {
  //     this.zone.run(()=>{
  //       this.Vehicles = data

  //     })
    
  // });


  }

}
