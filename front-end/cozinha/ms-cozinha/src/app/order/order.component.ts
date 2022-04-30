import { Component, OnInit } from '@angular/core';
import { OrderForm } from './models/order-form';
import { OrderService } from './service/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
  }

  form: OrderForm = {
    "idOrder": 0,
    "done": false,
    "descriptionOrder": ""
  }

  newOrder() {
    this.orderService.createOrder(this.form).subscribe(
      (res) => {
        console.log(res);
        
      }
    )
  }

}
