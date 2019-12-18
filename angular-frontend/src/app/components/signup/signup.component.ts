import {Component, OnInit, NgZone} from '@angular/core';
import {Router} from '@angular/router';
import {RegistrationService} from '../../services/registration.service';
import {Message} from '../../interfaces/message';

var bcrypt = require('bcryptjs');

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  private notmatch;
  private value;

  alreadyExists: boolean = false;
  userNotSaved: boolean = true;

  constructor(private regService: RegistrationService, private router: Router, private zone: NgZone) {
  }

  ngOnInit() {
  }

  registerNewUser(firstName, lastName, email, role, mobile) {
    console.log(role)
    if (firstName != "" && lastName != "" && email != "" && role != "none") {
      this.regService.registerNewUser(firstName, lastName, email, role, mobile).subscribe((data) => {
        this.zone.run(() => {
          if (data.message == "OK") {
            this.userNotSaved = false;
          } else {
            this.alreadyExists = true;
          }
        })
      })
    }
  }

}
