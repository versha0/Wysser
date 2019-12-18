import { Orderdata } from './../interfaces/orderdata';
import { RouteOrder } from './../interfaces/route-order';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Slots} from '../interfaces/slots';
import {Order} from '../interfaces/order';
import {environment} from '../../environments/environment';
import {DateDemand} from '../interfaces/date-demand';
import { Message } from '../interfaces/message';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private http: HttpClient) {
  }

  checkSlots(retailerId): Observable<DateDemand> {
    let url = environment.apiUrl + '/order/orders/slots?retailerId=' + retailerId;
    return this.http.get<DateDemand>(url);
  }

  saveOrder(customerName, customerNumber, customerAddress, orderVolume, deliveryDate, slotNumber, orderStatus, retailerId): Observable<Orderdata[]> {
    let url = environment.apiUrl + "/order/orders/save";
    
    console.log(orderStatus);
    console.log(customerNumber);
    let data = {
      "customerName": customerName,
      "customerAddress": customerAddress,
      "customerPhoneNumber": customerNumber,
      "orderVolume": orderVolume,
      "deliveryDate": deliveryDate,
      "slotNumber": slotNumber,
      "orderStatus": orderStatus,
      "retailerId": retailerId
    };

    let routeData = {
      "customerName": customerName,
      "customerAddress": customerAddress,
      "customerPhoneNumber": customerNumber,
      "orderVolume": orderVolume,
      "deliveryDate": deliveryDate,
      "slotNumber": slotNumber,
      "orderStatus": orderStatus,
      "wholesalerId": retailerId
    };
    // this.http.get<Message>(environment.apiUrl + ":9090/searchByRetailerIdAndSlot/" + retailerId + "/" + slotNumber);
    this.sendVehicleKafka(retailerId, slotNumber).subscribe(data=>{console.log(data)});
    
    // this.saveInRoute(routeData).subscribe(data => {console.log(data)});

    return this.http.post<Orderdata[]>(url, JSON.stringify(data), {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    });
  }

  getAllOrderData(retailerEmail) {
    // let url = 'assets/static/orders.json';
    let url = environment.apiUrl + "/order/orders/findAllOrdersOfRetailer?retailerId=" + retailerEmail
    return this.http.get<Order[]>(url);
  }

  getCompletedOrders(retailerEmail) {
    // let url = 'assets/static/delivered.json';
    let url = environment.apiUrl + "/order/orders/findOrdersByStatus?retailerId=" + retailerEmail + "&orderStatus=delivered"
    return this.http.get<Orderdata[]>(url);
  }

  getPendingOrders(retailerEmail) {
    //let url = 'assets/static/pending.json';
    let url = environment.apiUrl + "/order/orders/findOrdersByStatus?retailerId=" + retailerEmail + "&orderStatus=pending"
    return this.http.get<Order[]>(url);
  }

  sendVehicleKafka(retailerId, slotNumber){
    return this.http.get<Message>(environment.apiUrl + "/vehicledemand/searchByRetailerIdAndSlot/" + retailerId + "/" + slotNumber);
  }

  saveInRoute(routeData){
    let data = {
      "customerName": routeData.customerName,
      "customerAddress": routeData.customerAddress,
      "customerPhoneNumber": routeData.customerNumber,
      "orderVolume": routeData.orderVolume,
      "deliveryDate": routeData.deliveryDate,
      "slot": routeData.slotNumber,
      "orderStatus": routeData.orderStatus,
      "wholesalerId": routeData.retailerId,
      "id" : routeData.id,
      "orderId": routeData.id
    };
    let url2 = environment.apiUrl + "/route/api/v1/order";
    return this.http.post<RouteOrder[]>(url2, JSON.stringify(data), {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    });
  }
}
