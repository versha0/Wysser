import {Injectable} from '@angular/core';
import {RetailerDetails} from './vehicledemanded';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environments/environment';
import {VehicleManagement} from "./interfaces/vehicle-management";
import {VehicleDemanded} from "./interfaces/vehicledemanded";

@Injectable({
  providedIn: 'root'
})
export class VehicledemandfrontendService {
  private url = environment.apiUrl + '/vehicledemand/save';

  constructor(private http: HttpClient) {
  }

  sendRetailerRequest(details: RetailerDetails) {
    return this.http.post(this.url, details, {
      responseType: 'text'
    });
  }
  sendnewRetailerRequest(retailerdemand: VehicleDemanded) {
    const myurl = environment.apiUrl + '/vehicledemand/savenewdemand';
    return this.http.post(myurl, retailerdemand);
  }
  searchByRetailerId(retailerId: number) {
    const myurl = environment.apiUrl + '/vehicledemand/searchbyretailerid/' + retailerId;
    return this.http.get(myurl);
  }
  searchByRetailerIdinrejected(retailerId: number) {
    const myurl = environment.apiUrl + '/vehicledemand/searchbyretaileridinrejected/' + retailerId;
    return this.http.get(myurl);
  }

}
