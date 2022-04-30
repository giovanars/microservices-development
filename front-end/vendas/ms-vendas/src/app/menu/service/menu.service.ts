import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


import { Menus } from '../model/menu-response';
import { NewMenuForm } from '../model/new-menu-form';
import { NewMenuResponse } from '../model/new-menu-response';

const API = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Menus> {
    return this.http.get<Menus>(`${API}/menu`);
  }

  newMenu(form: NewMenuForm): Observable<NewMenuResponse>  {
    return this.http.post<NewMenuResponse>(`${API}/menu`, form);
  }
}
