import { Driver } from './../interfaces/driver';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {Deliveryroute} from '../interfaces/deliveryroute';

@Injectable({
  providedIn: 'root'
})
export class DriverDashboardService {

  constructor(private http: HttpClient) {
  }

  getDeliveryRoute(date, vehicleId, timeslot): Observable<Deliveryroute> {
    let uri = environment.apiUrl + "/driver/dashboard/getNextDeliveryRoute?date=" + date + "&vehicleId=" + vehicleId + "&timeslot=" + timeslot;
    return this.http.get<Deliveryroute>(uri);
  }

  acceptDelivery(vehicleId: String) {
    let uri = environment.apiUrl + "/driver/dashboard/accept";
    let data = {"vehicleId": vehicleId};

    return this.http.post(uri, JSON.stringify(data), {headers: {'Content-Type': 'application/json'}});
  }

  rejectDelivery(vehicleId: String) {
    let uri = environment.apiUrl + "/driver/dashboard/reject";
    let data = {"vehicleId": vehicleId};

    return this.http.post(uri, JSON.stringify(data), {headers: {'Content-Type': 'application/json'}});
  }

  authenticateDriver(username: String, password: String){
    let uri = environment.apiUrl + "/vehicle/api/v1/findByVehicleNumber/" + username;

    return this.http.get<Driver>(uri);
  }
}
