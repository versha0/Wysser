import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { from } from 'rxjs';
import { Message } from '../interfaces/message';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(public http: HttpClient) { }

  findallrequested(companyName): Observable<any> {
    // const url =  'http://172.23.239.62:9090/findAll';

    const url = environment.apiUrl + '/vehicledemand/findAll/' + companyName;
    console.log(url); 
    console.log(companyName);
    return this.http.get(url);
  }

  sendAccept(input:any): Observable<Message>{
    const uri = environment.apiUrl + '/vehicle/api/v1/Accept';
   return this.http.post<Message>(uri, input);
  }


// ...................save vehicle after accepted by vehicle company.................

  saveAcceptedVehicle(input:any): Observable<Message>{
    const uri = environment.apiUrl + '/vehicle/api/v1/AcceptedVehicle';
    return this.http.post<Message>(uri,input);
  }


  getAcceptedVehicle(companyName):Observable<Message>{
    const uri = environment.apiUrl + '/vehicle/api/v1/AcceptedVehicle/' + companyName;
    return this.http.get<Message>(uri);
  }


  // saveAcceptedVehicle():Observable<any>{
  //   const url = environment.apiUrl + ':8095/api/v1/AcceptedVehicle';
  //   return this.http.post(url);
  // }




  sendAccepttovehicledemand(input:any){
    const uri = environment.apiUrl + '/vehicledemand/saveacceptedstatusdemand';
   return this.http.post(uri, input);
  }


  deleteinretailerdemand(id:number){
    const uri= environment.apiUrl + '/vehicledemand/deletedemand/'+ id;
    return this.http.delete(uri);
  }

  sendReject(input){

    const uri = environment.apiUrl + '/vehicle/api/v1/Reject';
    return this.http.post(uri, input);

  }

//...................save vehicle after rejected vehicle by vehicle company............

  saveRejectVehicle(input:any): Observable<Message>{
    const uri = environment.apiUrl + '/vehicle/api/v1/RejectedVehicle';
    return this.http.post<Message>(uri,input);
  }


  // saveReject(input){

  //   const uri = environment.apiUrl + ':8095/api/v1/RejectedVehicle';
  //   return this.http.post(uri, input);

  // }

  sendRejecttovehicledemand(input:any){
    const uri = environment.apiUrl + '/vehicledemand/saverejectedstatusdemand';
   return this.http.post(uri, input);
  }

  //getAcceptedVehicle(companyName)


}
