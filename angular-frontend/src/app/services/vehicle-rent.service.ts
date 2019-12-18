import { environment } from './../../environments/environment';
import { VehicleManagement } from 'src/app/interfaces/vehicle-management';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Vehicle} from '../interfaces/vehicle';


@Injectable({
  providedIn: 'root'
})
export class VehicleRentService {

  constructor(private http: HttpClient) {
  }

  getBookedVehicles(retailerId) {
    // let url = 'assets/static/vehicles.json';
    let url = environment.apiUrl + '/vehicledemand/searchbyretaileridinaccepted/' + retailerId;
    return this.http.get<VehicleManagement[]>(url);
  }

  getVCResponse(capacity: number, slot: string) {
    let url = 'assets/static/vehicleCompanyResponse.json';
    return this.http.get<VehicleManagement[]>(url);
  }
}
