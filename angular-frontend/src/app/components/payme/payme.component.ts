
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';


declare var paypal;

@Component({
  selector: 'app-payme',
  templateUrl: './payme.component.html',
  styleUrls: ['./payme.component.css']
})
export class PaymeComponent implements OnInit {
  @ViewChild('paypal', { static: true }) paypalElement: ElementRef;

  constructor(
   ) { }

 product = 
   {
    price: 500,
    description: 'One Year Subscription @ INR 500',
    img: '../../../assets/images/fastdelivery1.png'
  };




  paidFor = false;

  ngOnInit() {
    paypal
      .Buttons({
        createOrder: (data, actions) => {
          return actions.order.create({
            purchase_units: [
              {
                description: this.product.description,
                amount: {
                  currency_code: 'INR',
                  value: this.product.price
                }
              }
            ]
          });
        },
        onApprove: async (data, actions) => {
          const order = await actions.order.capture();
          this.paidFor = true;
          console.log(order);
        },
        onError: err => {
          console.log(err);
        }
      })
      .render(this.paypalElement.nativeElement);
  }
}
