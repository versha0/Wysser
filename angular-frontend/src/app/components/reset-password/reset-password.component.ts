import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  init: boolean = true;
  resetSuccessfull: boolean = false;
  wrongEmail = false;
  emptyField = false;

  constructor(private router: Router, private zone: NgZone, private regService: RegistrationService) { }

  ngOnInit() {
  }

  resetPassword(email) {
    if (email == "") {
      this.emptyField = true;
      setTimeout(() => {
          this.emptyField = false;
        },
        3000);
    } else {
      this.regService.resetPassword(email).subscribe((data) => {
        this.zone.run(() => {
          if (data.message == "OK") {
            this.resetSuccessfull = true;
            this.init = false;
            setTimeout(() => {
                this.router.navigate(['/home']);
              },
              5000);
          }
        })
      })
    }
  }
}
