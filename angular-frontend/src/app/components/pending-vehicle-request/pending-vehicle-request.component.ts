import { Component, OnInit, NgZone } from '@angular/core';
import {VehicledemandfrontendService} from '../../vehicledemandfrontend.service';
import {VehicleDemanded} from '../../interfaces/vehicledemanded';
import {Observable, Subscription} from 'rxjs';
import {DecodedJwtData} from '../../interfaces/decoded-jwt-data';
import * as jwt_decode from 'jwt-decode';
@Component({
  selector: 'app-pending-vehicle-request',
  templateUrl: './pending-vehicle-request.component.html',
  styleUrls: ['./pending-vehicle-request.component.css']
})
export class PendingVehicleRequestComponent implements OnInit {
  vehicledemanded: any = new Array<any>();
  vehicledemanded1: any = new Array<any>();

  constructor(private vehicledemandfrontendService: VehicledemandfrontendService, private zone: NgZone) { }

  decodedData: DecodedJwtData;
  ngOnInit() {

    this.decodedData = jwt_decode(localStorage.getItem('token'));

    this.vehicledemanded = this.vehicledemandfrontendService.searchByRetailerId(this.decodedData.userId).subscribe(
      vehicle => {
        this.zone.run(()=>{
          this.vehicledemanded = vehicle;
        })
        
    });
    this.vehicledemanded1 = this.vehicledemandfrontendService.searchByRetailerIdinrejected(this.decodedData.userId).subscribe(
      vehicle => {
        this.zone.run(()=>{
          this.vehicledemanded1 = vehicle;
        })
        
      });
  }

}
