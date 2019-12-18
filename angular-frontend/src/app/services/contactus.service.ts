import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {PendingResponse} from "../interfaces/pendingreponse";

@Injectable({
  providedIn: 'root'
})
export class ContactusService {

  constructor(private http: HttpClient) { }
   getpendingresponses() {
    const url = environment.apiUrl + '/admin/getNotreplied';
    return this.http.get(url);
}
  // getpendingreponses{
  //  const url = environment.apiUrl + ':7070/getNotreplied';
  //  return this.http.get(url);
  // }

}
