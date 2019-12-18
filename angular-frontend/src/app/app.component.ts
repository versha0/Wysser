import { Component, NgZone } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Wysser';
  isNotDriver: boolean = true;
  constructor(private zone: NgZone) {

  }

  ngOnInit() {
    if(localStorage.getItem("driver")){
      this.zone.run(()=>{
        this.isNotDriver = false;
      })
    }
  }


}
