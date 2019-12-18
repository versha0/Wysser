import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {VehicleManagement} from '../interfaces/vehicle-management';
import {environment} from 'src/environments/environment';
import {Vehicle} from '../interfaces/vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private upersons: VehicleManagement[] = [
    // {
    //     id: 1,
    //     vehicleNumber:'A302',
    //     driverName: 'Rajender',
    //     vehicleType: 'car',
    //     capacity: 100,
    //     costPerSlot: 100,
    //     vehicleStatus: 'Busy'
    // },
    // {
    //   id: 2,
    //   vehicleNumber:'A303',
    //   driverName: 'Arkov',
    //   vehicleType: 'truck',
    //   capacity: 500,
    //   costPerSlot: 500,
    //   vehicleStatus: 'free'
    // }

  ];
  private myUrl: string;

  constructor(public http: HttpClient) {
  }

  getVehiclesFromData(): VehicleManagement[] {
    return this.upersons;
  }


  addVehicle(vehicle: VehicleManagement) {
    // vehicle.id = this.upersons.length + 1;
    this.upersons.push(vehicle);

   

    const uri = environment.apiUrl + '/vehicle/api/v1/vehicle';

    this.http.post(uri, vehicle).subscribe();

  }

  updateVehicle(vehicle: VehicleManagement) {
    const index = this.upersons.findIndex(u => vehicle.id === u.id);
    this.upersons[index] = vehicle;
  }

  deleteVehicle(vehicle: VehicleManagement) {
    this.upersons.splice(this.upersons.indexOf(vehicle), 1);

    console.log(vehicle.id);

    const url = environment.apiUrl + '/vehicle/api/v1/vehicle/' + vehicle.id;

    this.http.delete(url).subscribe();
  }

  getAllVehicles(companyName): Observable<any> {
    const url = environment.apiUrl + '/vehicle/api/v1/findVehicles/' + companyName;
    console.log(url);
    return this.http.get(url);
  }

  getAll(){
    const url = environment.apiUrl + '/vehicle/api/v1/vehicles';

    return this.http.get(url);
  }
  getvolumeandslot1(capacity: number, slot1: string): Observable<any> {
    this.myUrl = environment.apiUrl + '/vehicle/api/v1/queryslot1/'  + capacity + '/' + 'available';
    return this.http.get(this.myUrl);
  }
  getvolumeandslot2(capacity: number, slot2: string): Observable<any> {
    this.myUrl = environment.apiUrl + '/vehicle/api/v1/queryslot2/'  + capacity + '/' + 'available';
    return this.http.get(this.myUrl);
  }
  getvolumeandslot3(capacity: number, slot3: string): Observable<any> {
    this.myUrl = environment.apiUrl + '/vehicle/api/v1/queryslot3/'  + capacity + '/' + 'available';
    return this.http.get(this.myUrl);
  }
}
