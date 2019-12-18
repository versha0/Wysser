import { Orderdata } from './../../interfaces/orderdata';
import {DateDemand} from '../../interfaces/date-demand';
import {Component, OnInit, NgZone} from '@angular/core';
import {OrderServiceService} from '../../services/order-service.service';
import {catchError, tap} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {Order} from '../../interfaces/order';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
  //retailerId will depend on saved cookie

  retailerId: string = "";
  retailerEmail: string = "";
  success: boolean = false;
  //property which indicates whether a given slot can be picked
  slotValid = {"slot1": true, "slot2": true, "slot3": true};
  slotJson: DateDemand = {
    "date": "",
    "timeslot1": {
      "slot": "11:00-13:00",
      "volume": undefined
    },
    "timeslot2": {
      "slot": "14:00-16:00",
      "volume": undefined
    },
    "timeslot3": {
      "slot": "17:00-19:00",
      "volume": undefined
    }
  };

  slot1: boolean = false;
  slot2: boolean = false;
  slot3: boolean = false;

  orderVolume: number = 5;
  address: string = "Adugodi";
  selectedSlot: string;
  date = new Date(Date.now());
  today = new Date(this.date.getTime() - (this.date.getTimezoneOffset() * 60000)).toISOString().split("T")[0];

  savedOrder: Orderdata[];

  constructor(private orderService: OrderServiceService, private zone: NgZone) {
  }

  ngOnInit() {
    // getting email from token
    var decoded = {
      "userId": "",
      "sub": ""
    }
    let token = localStorage.getItem('token');

    if (token != null) {
      decoded = jwt_decode(token);
      this.retailerId = decoded.userId;
      this.retailerEmail = decoded.sub;
      console.log(this.retailerEmail);
    }
    this.orderService.checkSlots(this.retailerId).subscribe(data =>
      this.zone.run(() => {
        console.log(data);
        this.slotJson = data;
        this.changeSlotValue(this.orderVolume);
        console.log(this.slotJson);
      }));
    this.checkSlotsOnDate(this.retailerId);
    this.orderService.sendVehicleKafka(this.retailerId, this.selectedSlot).subscribe(data => {
      console.log(data);
    });
  }

  checkSlotsOnDate(retailerId) {
    console.log(retailerId);
    this.orderService.checkSlots(retailerId).subscribe(data =>
      this.zone.run(() => {
        console.log(data);
        this.slotJson = data;
        this.changeSlotValue(this.orderVolume);
        console.log(this.slotJson);
      }));
    console.log(this.slotJson);
  }

  changeSlotValue(orderVolume) {
    console.log(orderVolume);
    if (orderVolume > this.slotJson["timeslot1"]["volume"]) {
      this.slotValid["slot1"] = false;
    } else {
      this.slotValid["slot1"] = true;
    }
    if (orderVolume > this.slotJson["timeslot2"]["volume"]) {
      this.slotValid["slot2"] = false;
    } else {
      this.slotValid["slot2"] = true;
    }
    if (orderVolume > this.slotJson["timeslot3"]["volume"]) {
      this.slotValid["slot3"] = false;
    } else {
      this.slotValid["slot3"] = true;
    }
  }

  setSlot(slotNumber: string) {
    if (this.selectedSlot != slotNumber) {
      this.selectedSlot = slotNumber;
    } else {
      this.selectedSlot = "";
    }
    console.log(this.selectedSlot);
  }

  submitOrder(customerName, customerNumber, customerAddress, orderVolume): void {
    console.log(this.orderVolume);
    console.log(this.selectedSlot);
    if (customerName != "" && customerNumber != "" && customerAddress != "" && orderVolume != null && this.selectedSlot != "") {
      console.log(this.orderVolume);
      this.orderService.saveOrder(customerName, customerNumber, customerAddress, orderVolume, this.today, this.selectedSlot, "pending", this.retailerId)
        .pipe(
          tap((newOrder) => {
            console.log(newOrder);
            this.savedOrder = newOrder
          }),
          catchError(error => {
            return Observable.throw("Something went wrong" + JSON.stringify(error));
          })
        ).subscribe(data => {
        this.success = true;
        let form = <HTMLFormElement>document.getElementById("orderForm");
        form.reset();
        this.slot1 = false;
        this.slot2 = false;
        this.slot3 = false;
        // this.slotJson = null;
        this.checkSlotsOnDate(this.retailerId);
        this.slotValid = {"slot1": true, "slot2": true, "slot3": true};
        this.orderService.saveInRoute(this.savedOrder).subscribe(route => {
          console.log(route);
        });
      });
    }
  }
}
