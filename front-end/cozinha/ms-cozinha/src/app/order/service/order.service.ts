import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { OrderResponse } from '../models/order-response';
import { OrderForm } from '../models/order-form';

const API = "http://localhost:8081";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  createOrder(form: OrderForm): Observable<OrderResponse>{
    return this.http.post<OrderResponse>(`${API}/kitchen`, form);
  }
}
