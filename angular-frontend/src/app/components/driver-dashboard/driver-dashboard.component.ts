import {DriverDashboardService} from '../../services/driver-dashboard.service';
import {Component, OnInit, NgZone} from '@angular/core';
import {Deliveryroute} from '../../interfaces/deliveryroute';
import {Router} from '@angular/router';


@Component({
  selector: 'app-driver-dashboard',
  templateUrl: './driver-dashboard.component.html',
  styleUrls: ['./driver-dashboard.component.css']
})
export class DriverDashboardComponent implements OnInit {
  deliveryRoute: Deliveryroute;
  vehicleId = 'KA123';
  today: String = new Date().toISOString().slice(0, 10);
  timeslot: String = "Slot1";
  newDelivery: boolean = false;

  constructor(private zone: NgZone, private dashboardService: DriverDashboardService,
              private router: Router) {
  }

  ngOnInit() {
    this.dashboardService.getDeliveryRoute(this.today, this.vehicleId, this.timeslot).subscribe(
      data => {
        this.zone.run(() => {
            this.deliveryRoute = data;
            this.newDelivery = true;
            console.log(JSON.stringify(this.deliveryRoute))
          }
        );
      }
    );
  }

  acceptDelivery() {
    this.dashboardService.acceptDelivery(this.vehicleId).subscribe(() => {
        this.router.navigate(['/deliveryPickup', this.deliveryRoute])
      },
      (error) => {
        console.log(error)
      });
  }

  rejectDelivery() {
    this.dashboardService.rejectDelivery(this.vehicleId).subscribe(() => {
        this.zone.run(() => {
            this.deliveryRoute = null;
            this.newDelivery = false;
            console.log(JSON.stringify(this.deliveryRoute))
          }
        )
      },
      (error) => {
        console.log(error)
      });
  }

}
