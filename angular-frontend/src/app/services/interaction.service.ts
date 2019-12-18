import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteractionService {
  data:any;
  messageSource = new BehaviorSubject<string>('initial value');
  message = this.messageSource.asObservable();
  messageSource1 = new BehaviorSubject<string>('initial value');
  // message1 = this.messageSource1.asObservable();

  constructor() { }
  sendMessage(message: string) {
    this.messageSource.next(message);
  }
  sendcontactusdetailes(data) {
    this.messageSource1 = data;
    // this.messageSource1.asObservable();
  }

}
