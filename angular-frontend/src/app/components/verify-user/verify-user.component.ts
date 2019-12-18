import {Component, OnInit, NgZone} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RegistrationService} from '../../services/registration.service';

@Component({
  selector: 'app-verify-user',
  templateUrl: './verify-user.component.html',
  styleUrls: ['./verify-user.component.css']
})
export class VerifyUserComponent implements OnInit {

  tokenVerified: boolean = false;
  invalidToken: boolean = false;
  updateSuccessfull: boolean = false;

  private userToken: string;
  passwordMismatch: boolean = false;
  emptyField: boolean = false;

  constructor(private router: Router, private zone: NgZone, private activatedRoute: ActivatedRoute, private regService: RegistrationService) {
  }

  ngOnInit() {
    this.userToken = this.activatedRoute.snapshot.paramMap.get('token');
    this.regService.confirmToken(this.userToken).subscribe((data) => {
      this.zone.run(() => {
        if (data.message == "OK") {
          this.tokenVerified = true;
        } else {
          this.invalidToken = true;
        }
      })
    })
  }

  setPassword(password, confirmpassword) {
    if (password == "" || confirmpassword == "") {
      this.emptyField = true;
      setTimeout(() => {
          this.emptyField = false;
        },
        3000);
    } else if (password != confirmpassword) {
      this.passwordMismatch = true;
      setTimeout(() => {
          this.passwordMismatch = false;
        },
        3000);
    } else {
      this.regService.updatePassword(password, this.userToken).subscribe((data) => {
        this.zone.run(() => {
          if (data.message == "OK") {
            this.updateSuccessfull = true;
            this.tokenVerified = false;
            setTimeout(() => {
                this.router.navigate(['/login']);
              },
              5000);
          }
        })
      })
    }
  }

}
