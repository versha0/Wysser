import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GetlistService {

  constructor(private http: HttpClient) {
  }

  postLists(email, mobile, pass) {
    console.log(pass);
    let data = {"email": email, "mobileNum": mobile, "password": pass};
    return this.http.post(environment.apiUrl + ':8080/api/v1/save', JSON.stringify(data), {headers: {'Content-Type': 'application/json'}})
  }
}
