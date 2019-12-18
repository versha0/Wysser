import { Component, OnInit, NgZone } from '@angular/core';
import {Orderdata} from '../../interfaces/orderdata';
import {Router} from '@angular/router';
import {InteractionService} from '../../services/interaction.service';
import {DataorderService} from '../../services/dataorder.service';
import {MatDialog} from '@angular/material/dialog';
import {CanceldialogueComponent} from '../canceldialogue/canceldialogue.component';
import {DatafromrouteoptimizerService} from '../../services/datafromrouteoptimizer.service';

@Component({
  selector: 'app-maindriverdashboard',
  templateUrl: './maindriverdashboard.component.html',
  styleUrls: ['./maindriverdashboard.component.css']
})
export class MaindriverdashboardComponent implements OnInit {
  temp = new Array<any>();
  flagforallcompleted = false;
  // vehicleId: string;
  retailerId: string;
  slot = 'slot1';
  currentorder = 0;
  vehicleId = localStorage.getItem('vehicleNumber');
  depotAddress = '';
  orderData: any[];
  orderRoutes = [];

  route: any;
  constructor(private router: Router,
              private interactionserv: InteractionService,
              private dataorder: DataorderService,
              public dialog: MatDialog,
              private datafromoptimizer: DatafromrouteoptimizerService,
              private zone: NgZone) {
  }

  ngOnInit() {
    this.route = JSON.parse(localStorage.getItem('route'));
    console.log(this.route);
    if (this.route == null) {
      this.temp[0] = new Orderdata();
      this.temp[1] = new Orderdata();
      this.datafromoptimizer.getorderdatafromrouteoptimizer(this.vehicleId).then(
        (result: any[]) => {
          this.zone.run(()=>{
            this.orderData = result;
            console.log(this.orderData)
            this.processOrder();
          })
          
        }
      );
      } else {
        this.updateView();
      }
  }



  processOrder() {
    console.log(this.orderData);
    this.orderData.forEach(
      (order: any) => {
        this.temp[0].customerAddress = order.depotAddress;
        localStorage.setItem('depotAddress', order.depotAddress);
        console.log(this.temp[0].customerAddress);
        const processedString = order.routes.toString().replace('[', '').replace(']', '');
        processedString.split('{').forEach(
          addr => {
            if (addr.length !== 0) {
              const routeX = {};
              addr.replace('}', '').split(',').forEach(
                propertyValue => {
                  const property = propertyValue.split(':')[0]
                    .replace('"', '')
                    .replace('"', '');
                  // noinspection UnnecessaryLocalVariableJS
                  const value = propertyValue.split(':')[1];
                  if (property.length !== 0) {
                    routeX[property] = value;
                  }
                });
              this.orderRoutes.push(routeX);
            }
          });
        console.log(this.orderRoutes);
        // modify routes
        localStorage.setItem('route', JSON.stringify(this.orderRoutes));
        this.route = JSON.parse(localStorage.getItem('route'));
        console.log(this.route[0].customerAddress);
        this.temp[1].orderId = this.route[0].orderId;
        this.temp[1].customerAddress = this.route[0].customerAddress;
        console.log(this.temp[1].customerAddress);

        for (let i = 0; i < this.route.length; i++) {
          if (this.route[i].orderStatus === '"delivered"') {
            this.currentorder = i;
            this.temp[0] = this.orderData[i];
            this.temp[1] = this.orderData[i + 1];
            console.log(this.temp[0]);
            console.log(this.temp[1]);
            // break;
          }
        }
      }
    );
  }

  updateView() {
    var flag = false;
    console.log('test');
    console.log(this.route[0].orderStatus);
    this.temp[0] = new Orderdata();
    this.temp[1] = new Orderdata();
    for (let i = 0; i < this.route.length; i++) {
      // if (i == (this.route.length - 1)) {
      //   this.flagforallcompleted = true;
      // }
      if (this.route[i].orderStatus === '"delivered"') {
        this.currentorder = i + 1;
        this.temp[0] = this.route[i];
        this.temp[1] = this.route[i + 1];
        console.log(this.temp[0]);
        console.log(this.temp[1]);
        flag = true;

      }
    }
    if(this.route[this.route.length - 1].orderStatus == '"delivered"'){
      this.flagforallcompleted = true;
    }
    if (flag == false) {
      this.temp[0].customerAddress = localStorage.getItem('depotAddress');
      this.temp[1] = this.route[0];
    }

  }

  navigate() {
    this.interactionserv.sendMessage(this.temp[0].customerAddress + ' ' + this.temp[1].customerAddress);
    this.router.navigateByUrl('navigate');
  }

  signature() {
    // for(let vehicle of this.orderData){
    // }
    // this.orderRoutes[this.currentorder].orderStatus = '"delivered"';
    this.route[this.currentorder].orderStatus = '"delivered"';
    localStorage.setItem('route', JSON.stringify(this.route));
    // console.log(this.orderRoutes[this.currentorder]);
    this.dataorder.updateOrderStatus(this.route[this.currentorder].orderId,
      this.route[this.currentorder].orderStatus)
      .toPromise().then(
      result => {
        console.log(result);
      },
      reason => {
        console.log(reason);
      });

    this.dataorder.deleteOrder(this.route[this.currentorder].orderId).subscribe(data=>{
      console.log(data);
    })
    // console.log(this.orderData);
    this.router.navigateByUrl('signature');
  }

  //
  openDialogue() {
    this.dialog.open(CanceldialogueComponent);
  }

  //
  delayDelivery() {
    this.orderRoutes[this.currentorder].orderStatus = 'delayed';
    this.dataorder.updateOrderStatus(this.orderRoutes[this.currentorder].orderId,
      this.orderRoutes[this.currentorder].orderStatus).toPromise().then(
      result => {
        console.log(result);
      },
      reason => {
        console.log(reason);
      });
    console.log(this.orderData);
    this.router.navigateByUrl('driverdashboard');
  }

  isPending(data) {
    if (data.orderStatus == '"pending"') {
      return true;
    }
    return false;
  }
}
