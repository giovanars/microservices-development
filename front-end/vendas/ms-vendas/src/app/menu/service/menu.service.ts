import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { MenuResponse } from '../model/menu-response';

const API = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<MenuResponse> {
    return this.http.get<MenuResponse>(`${API}/menu`);
  }
}
