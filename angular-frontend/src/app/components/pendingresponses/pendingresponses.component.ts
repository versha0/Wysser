import { Component, OnInit } from '@angular/core';
import {ContactusService} from '../../services/contactus.service';
import {PendingResponse} from '../../interfaces/pendingreponse';
import {Observable} from 'rxjs';
import {MatDialog} from '@angular/material/dialog';
import {ContactusreplypopupComponent} from "../contactusreplypopup/contactusreplypopup.component";
import {InteractionService} from "../../services/interaction.service";

@Component({
  selector: 'app-pendingresponses',
  templateUrl: './pendingresponses.component.html',
  styleUrls: ['./pendingresponses.component.css']
})
export class PendingresponsesComponent implements OnInit {
  pendingtemp: any;
  constructor(private contactusService: ContactusService,
              private matdialogue: MatDialog,
              private interaction:InteractionService) { }

  ngOnInit() {
    this.contactusService.getpendingresponses().subscribe(pending => {
      this.pendingtemp = pending;

      console.log(this.pendingtemp);

    });
  }

  sendmail(data: PendingResponse) {
    this.interaction.sendcontactusdetailes(data);
    this.matdialogue.open(ContactusreplypopupComponent);

    // const urlgmail = 'https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&to=' + data.email + '&su=' + data.subject;
    // window.open(urlgmail);

  }
}
