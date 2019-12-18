import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {Observable} from "rxjs";
import { DecodedJwtData } from '../interfaces/decoded-jwt-data';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class EditProfileService {

  constructor(public http: HttpClient) {
  }

  public saveRetailerData(data) {

    const url = environment.apiUrl + '/profile/retailerProfile/saveDetailOfRetailer';
    // console.log(url);
    console.log('profile data = ');
    console.log(data);

    setTimeout(() => {
      this.http.post(url, data).subscribe();
      // alert('Profile Saved');
    }, 60);


  }



  public getProfileFromEmail(data) {

    const url = environment.apiUrl + '/profile/retailerProfile/getRetailerFromEmail?email=' + data;

    return this.http.get(url);

  }
 public getAllRetailer(): Observable<any> {
    const url = environment.apiUrl + '/profile/retailerProfile/getAllRetailersProfileList';
    return this.http.get(url);
 }

 public deleteRetailerAccount(){
   var decodedData:DecodedJwtData;
   decodedData = jwt_decode(localStorage.getItem('token'));

   let url = environment.apiUrl+'/profile/retailerProfile/deleteAccount?id='+decodedData.userId;

   return this.http.delete(url).subscribe();



 }



 public getRetailerProfilebyId(Retailerid){
    let uri =  environment.apiUrl+'/profile/retailerProfile/getRetailerById?id='+Retailerid;
    return this.http.get(uri);
 }






}
