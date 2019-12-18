import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {

  username: any;
  password: any;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  checkLogin() {
    // tslint:disable-next-line:triple-equals
    if (this.username === 'admin' && this.password === 'root123') {
      this.router.navigateByUrl('statistics');
    } else {
      alert('please enter correct username and password');
    }
  }
}
