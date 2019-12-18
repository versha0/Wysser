import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';


// const httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json',
//     'Authorization': 'Token '+localStorage.getItem('token')
//   })
// };


@Injectable({
  providedIn: 'root'
})
export class TokenFilterCheckService {

  constructor(private http:HttpClient) { }



  sendTokenWithHeader(){

      // return this.http.get('http://localhost:9091/rest/hello',httpOptions);

      return this.http.get(environment.apiUrl+'/auth/rest/hello');

  }
}
