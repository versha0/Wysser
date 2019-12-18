import { Component, OnInit } from '@angular/core';
import {PendingResponse} from '../../interfaces/pendingreponse';
import {AdminService} from '../../services/admin.service';
@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit {
  pendingrequest = new PendingResponse();
  name: any;
  email: any;
  subject: any;
  message: any;

  constructor(private adminservice: AdminService) { }
  ngOnInit() {


  }
// this.pendingrequest.replystatus = false;




  sendandsave() {
    this.pendingrequest.replyStatus = false;

    this.pendingrequest.name = this.name;
    this.pendingrequest.email = this.email;
    this.pendingrequest.message = this.message;
    this.pendingrequest.subject = this.subject;
    console.log(this.pendingrequest);
    this.adminservice.postnewcontactus(this.pendingrequest).subscribe();
  }
}
