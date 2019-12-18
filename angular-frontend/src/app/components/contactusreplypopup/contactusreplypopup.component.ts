import {Component, OnInit} from '@angular/core';
import {InteractionService} from '../../services/interaction.service';
import {PendingResponse} from '../../interfaces/pendingreponse';
import {BehaviorSubject, Subscription} from 'rxjs';
import {Router} from "@angular/router";
import {AdminService} from "../../services/admin.service";

@Component({
  selector: 'app-contactusreplypopup',
  templateUrl: './contactusreplypopup.component.html',
  styleUrls: ['./contactusreplypopup.component.css']
})
export class ContactusreplypopupComponent implements OnInit {
  data: any;
  body1: string;
  constructor(private interaction: InteractionService,
              private router: Router,
              private sendemail: AdminService) {
  }

  ngOnInit() {
  }

  sendreply() {

    this.data = this.interaction.messageSource1;
    this.sendemail.sendemail(this.data.email, this.data.subject, this.body1);
    this.data.replyStatus = true;
    this.sendemail.postnewcontactus(this.data).subscribe();
  }
}
