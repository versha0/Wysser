import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as jwt_decode from 'jwt-decode';
import { JwtHelperService } from "@auth0/angular-jwt";
import { Router } from '@angular/router';
import { DecodedJwtData } from '../interfaces/decoded-jwt-data';

const helper = new JwtHelperService();



@Injectable({
  providedIn: 'root'
})
export class LoginAuthService {


  constructor(private httpclient: HttpClient, private router: Router) {
  }


  getToken(data) {

    let url = environment.apiUrl + '/auth/token/generate';

    return this.httpclient.post(url, data)


  }

  isTokenValid(){
    // write token validation logic goes here
    return true;
  }


  isTokenExpired() {
    let token = localStorage.getItem('token')
    let decoded = { "sub": "", "exp": "" };

    decoded = jwt_decode(token);


    // const decodedToken = helper.decodeToken(token);
    // const expirationDate = helper.getTokenExpirationDate(token);

    const isExpired = helper.isTokenExpired(token);


    if (isExpired) {
      console.log("Token is Expired");
      alert("Session Expired!");
      localStorage.removeItem('token');
      this.router.navigate(['/login']);
    }

    return !isExpired;
  }


  logOut() {
    localStorage.removeItem('token')
  }

  
  deleteCredentials(){

    var decodedData:DecodedJwtData;
    decodedData = jwt_decode(localStorage.getItem('token'))

    let url = environment.apiUrl+"/auth/token/deleteCredentials?id="+decodedData.userId;

    this.httpclient.delete(url).subscribe();

    localStorage.removeItem('token');

  }



}
