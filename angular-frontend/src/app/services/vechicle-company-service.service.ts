import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';
import { DecodedJwtData } from '../interfaces/decoded-jwt-data';

@Injectable({
  providedIn: 'root'
})
export class VechicleCompanyServiceService {

  constructor(private http:HttpClient) { }

  getVehicleCompanyProfileFromEmail(email){
    console.log("vehicle profille get");
    let url = environment.apiUrl+"/profile/vehicleCompanyProfile/getProfile?email="+email;
    console.log(url)
    return this.http.get(url);
  }

  saveVehicleCompanyProfile(dataObj){
    console.log("vehicle profile save");
    let url = environment.apiUrl + "/profile/vehicleCompanyProfile/saveVehicleCompanyDetail";
    console.log(url)
    this.http.post(url,dataObj).subscribe();
  }

  deleteVehicleCompanyProfile(){

    var decodedData:DecodedJwtData;
    decodedData = jwt_decode(localStorage.getItem('token'));

    let url = environment.apiUrl + "/profile/vehicleCompanyProfile/deleteAccount?id="+decodedData.userId;

    this.http.delete(url).subscribe();
    
  }


}
