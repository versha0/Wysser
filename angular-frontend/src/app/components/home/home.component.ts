import {Component, NgZone} from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private route: ActivatedRoute, private zone: NgZone) {
    this.route.params.subscribe( params => console.log(params) );
  }

  ngOnInit() : void {
    this.zone.run(()=>{
      localStorage.removeItem("driver");
    })
    
  }

}
