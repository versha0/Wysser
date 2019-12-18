import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private router: Router,
              private http: HttpClient ) { }
   sendemail(email: any, sub: any, body: any) {
    const url = environment.apiUrl + '/admin/sendEmail?email=' + email + '&subject=' + sub + '&body=' + body;
    this.http.post(url, null).subscribe();
   }


   postnewcontactus(data: any)  {
    const url = environment.apiUrl + '/admin/savenewcontactusrequest';
    return this.http.post(url, data);

   }
}
