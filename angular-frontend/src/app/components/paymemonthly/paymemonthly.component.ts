
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';


declare var paypal;

@Component({
  selector: 'app-paymemonthly',
  templateUrl: './paymemonthly.component.html',
  styleUrls: ['./paymemonthly.component.css']
})
export class PaymemonthlyComponent implements OnInit {

  @ViewChild('paypal', { static: true }) paypalElement: ElementRef;

 
 product = 
   {
    price: 100,
    description: 'One Month Subscription',
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
