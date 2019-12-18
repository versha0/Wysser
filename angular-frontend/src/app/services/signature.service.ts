import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SignatureDetails} from "../interfaces/signature";

@Injectable({
  providedIn: 'root'
})
export class SignatureService {
  private url = environment.apiUrl + '/signature/save';
  constructor(private http: HttpClient) { }
  sendSignatureRequest(details: SignatureDetails) {
    return this.http.post(this.url, details, {
      responseType: 'text'
    });
  }
}
