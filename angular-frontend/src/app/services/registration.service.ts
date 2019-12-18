import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Message} from '../interfaces/message';
import {ActivatedRoute} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) {
  }

  registerNewUser(firstName, lastName, email, role, mobile): Observable<Message> {
    let uri = environment.apiUrl + "/registration/register";
    let data = {"firstName": firstName, "lastName": lastName, "email": email, "role": role};
    return this.http.post<Message>(uri, JSON.stringify(data), {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    });
  }

  confirmToken(token): Observable<Message> {
    let uri = environment.apiUrl + "/registration/confirm?token=" + token;
    return this.http.get<Message>(uri);
  }

  updatePassword(password, token) {
    let uri = environment.apiUrl + "/registration/confirm?token=" + token + "&password=" + password;
    // let data = {"password": password, "token": token};
    return this.http.post<Message>(uri, {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    });
  }

  resetPassword(email): Observable<Message>{
    let uri = environment.apiUrl + "/registration/forgot?email=" + email;
    return this.http.post<Message>(uri, {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    });
  }
}
