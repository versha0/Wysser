import { Component, OnInit } from '@angular/core';
import { TokenFilterCheckService } from 'src/app/services/token-filter-check.service';

@Component({
  selector: 'app-token-filter-check',
  templateUrl: './token-filter-check.component.html',
  styleUrls: ['./token-filter-check.component.css']
})
export class TokenFilterCheckComponent implements OnInit {

  constructor(private tokenFilter:TokenFilterCheckService) { }

  ngOnInit() {
  }


  sendToken(){
    console.log('in token check component');
    this.tokenFilter.sendTokenWithHeader().subscribe();
  }


}
