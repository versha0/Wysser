import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-cancelreason',
  templateUrl: './cancelreason.component.html',
  styleUrls: ['./cancelreason.component.css']
})
export class CancelreasonComponent implements OnInit {
  selected = 'option2';
  constructor(private router: Router) { }

  ngOnInit() {
  }
  submit() {
    alert('We received your reason successfully');
    this.router.navigateByUrl('driverdashboard');
  }
}
