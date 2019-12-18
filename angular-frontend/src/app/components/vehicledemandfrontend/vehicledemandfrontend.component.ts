import {Component, Inject, OnInit, NgZone} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {VehicledemandfrontendService} from '../../vehicledemandfrontend.service';
import {RetailerDetails} from '../../vehicledemanded';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {VehicleService} from '../../services/vehicle.service';
import {Observable} from 'rxjs';
import {VehicleRentService} from '../../services/vehicle-rent.service';
import * as jwt_decode from 'jwt-decode';
import {VehicleDemanded} from '../../interfaces/vehicledemanded';
import {VehicleManagement} from "../../interfaces/vehicle-management";
import { AdminService } from 'src/app/services/admin.service';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';

@Component({
  selector: 'app-vehicledemandfrontend',
  templateUrl: './vehicledemandfrontend.component.html',
  styleUrls: ['./vehicledemandfrontend.component.css']
})
export class VehicledemandfrontendComponent implements OnInit {
  capacity: number;
  retailerId: number;
  slot: any;
  // date : string;
  vehicleStatus: string;
  myUrl: any;
  // Queryresponse: Observable<any>;
  vehiclequery: Array<any> = new Array<any>();
  vehicleDemanded: VehicleDemanded;
  flag = false;

  decoded:DecodedJwtData;

  constructor(
    private dialog: MatDialog,
    private queryvehicleservice: VehicleService,
    private vehicleService: VehicledemandfrontendService,
    private datePipe: DatePipe,
    private router: Router,
    private http: HttpClient,
    private testservice: VehicleRentService,
    private zone: NgZone,
    private adminService:AdminService,
  ) {
    // this.date = this.datePipe.transform(this.date, 'yyyy-MM-dd');
  }
  success:boolean = false;
  ngOnInit() {
    // getting email from token

    let token = localStorage.getItem('token');
    if (token != null) {
      this.decoded = jwt_decode(token);
      this.retailerId = this.decoded.userId;
      console.log(this.retailerId);
    }

  }

  openDialog() {
    // this.dialog.open(RatecardpopupComponent);
  }

  sendRequest() {
    alert("Booking request sent!")
    const retailerDetails = new RetailerDetails();
    // retailerDetails.date = this.date;
    retailerDetails.timeSlot = this.slot;
    retailerDetails.volume = this.capacity;
    // console.log(retailerDetails.volume);
    // console.log(retailerDetails.timeSlot);
    // console.log(retailerDetails.date);
    this.vehicleService.sendRetailerRequest(retailerDetails).subscribe(
      result => 
      this.zone.run(()=>{
        console.log(result);
        this.success = true;
      })
    );
  }

  sendrequest() {
    // this.flag = true;
    // this.testservice.getVCResponse(this.capacity, 'available').subscribe(vehicle => {
    //       this.vehiclequery = vehicle;
    //       console.log(vehicle);
    //       console.log('in slot 1');
    //     });


    if (this.slot === 'slot1') {
      this.queryvehicleservice.getvolumeandslot1(this.capacity, 'available').subscribe(vehicle => {
        this.zone.run(() => {
          this.vehiclequery = vehicle;
        console.log(vehicle);
        console.log('in slot 1');
        this.flag = true;
        });

      });
    } else if (this.slot === 'slot2') {
     this.queryvehicleservice.getvolumeandslot2(this.capacity, 'available').subscribe(vehicle => {
        this.vehiclequery = vehicle;
        console.log(vehicle);
        console.log('in slot 2');
      });
    } else {
      this.queryvehicleservice.getvolumeandslot3(this.capacity, 'available').subscribe(vehicle => {
        this.vehiclequery = vehicle;
        console.log(vehicle);
        console.log('in slot3');
      });
    }

  }

  isRequestSent:boolean = false;
  mailBody:string;
  mailTitle:string;  

  demandrow(data: VehicleManagement) {
    console.log(data.capacity);
    this.vehicleDemanded = new VehicleDemanded();
    this.vehicleDemanded.capacity = data.capacity;
    this.vehicleDemanded.companyName = data.companyName;
    this.vehicleDemanded.costPerSlot = data.costPerSlot;
    this.vehicleDemanded.driverName = data.driverName;
    this.vehicleDemanded.retailerId = Number(this.retailerId);
    this.vehicleDemanded.id = data.id;
    this.vehicleDemanded.slot1 = data.slot1;
    this.vehicleDemanded.slot2 = data.slot2;
    this.vehicleDemanded.slot3 = data.slot3;
    this.vehicleDemanded.slot = this.slot;
    this.vehicleDemanded.vehicleNumber = data.vehicleNumber;
    this.vehicleDemanded.vehicleStatus = data.vehicleStatus;
    this.vehicleDemanded.vehicleType = data.vehicleType;
    console.log(this.vehicleDemanded);
    this.vehicleService.sendnewRetailerRequest(this.vehicleDemanded).subscribe(()=>{
      // alert("Booking request sent!");

      this.mailTitle="Wysser Vehicle Booking Request Processed";
      this.mailBody="Your booking request has been processed. We'll send you another email as soon as it has been accepted!";

      this.adminService.sendemail(this.decoded.sub,this.mailTitle,this.mailBody);

      this.isRequestSent=true;
    
      this.vehiclequery = null;
    });
  }
}
