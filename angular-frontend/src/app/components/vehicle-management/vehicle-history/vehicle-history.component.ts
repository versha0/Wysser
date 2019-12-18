import {Component, OnInit, NgZone} from '@angular/core';
import * as CanvasJS from '../../../../assets/js/canvasjs.min';
import { style } from '@angular/animations';
import { RequestService } from 'src/app/services/request.service';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import * as jwt_decode from 'jwt-decode';
import { VechicleCompanyServiceService } from 'src/app/services/vechicle-company-service.service';

@Component({
  selector: 'app-vehicle-history',
  templateUrl: './vehicle-history.component.html',
  styleUrls: ['./vehicle-history.component.css']
})
export class VehicleHistoryComponent implements OnInit {

	Vehicles: Array<any>;
  
	dataFromToken:DecodedJwtData;
	cName;
	vehicleCompanyData;


	  constructor(private requestService: RequestService, private zone:NgZone, private vehicleCompanyService: VechicleCompanyServiceService) {
  }


  ngOnInit() {
		let chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		exportEnabled: true,
		title: {
			text: "Accepted Vehicle"
		},
		data: [{
			type: "column",
			dataPoints: [
				{ y: 71, label: "KA110" },
				{ y: 55, label: "KA111" },
				{ y: 50, label: "KA112" },
				{ y: 65, label: "KA113" },
				{ y: 40, label: "KA114" },
				{ y: 20, label: "KA115" },
				{ y: 30, label: "KA116" },
				{ y: 75, label: "KA117" },
				{ y: 25, label: "KA118" },
				

			]
		}]
	});
		
	 chart.render();

  
	let chart1 = new CanvasJS.Chart("chartContainer1", {
		theme: "light2",
		animationEnabled: true,
		exportEnabled: true,
		title:{
			text: "Vehicles"
		},
		data: [{
			type: "pie",
			showInLegend: true,
			toolTipContent: "<b>{name}</b>: {y} (#percent%)",
			indexLabel: "{name} - #percent%",
			dataPoints: [
				{ y: 450, name: "Bike" },
				{ y: 400, name: "Car" },
				{ y: 300, name: "Truck" },
				{ y: 100, name: "Van" },
				
			
			]
		}]
	});
		
	chart1.render();


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



    




//   ngOnInit() {
//   }

// }
