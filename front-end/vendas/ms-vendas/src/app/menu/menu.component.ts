import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MenuResponse, Menus } from './model/menu-response';
import { NewMenuForm } from './model/new-menu-form';
import { MenuService } from './service/menu.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor(private menuService: MenuService) { }

  ngOnInit(): void {
    this.menus.pop();
    this.getAll();
  }

  menus: Menus = [{
    "createdAt": "",
    "description": "",
    "id": "",
    "name": "",
    "price": 0
  }];

  carga: NewMenuForm[] = [
    {
      "description": "Maminha, Arroz, Feijão, Salada e Farofa (Serve 2 pessoas)",
      "name": "Maminha",
      "price": 100
    },
    {
      "description": "Frango a parmegiana, Arroz, Feijão, Salada e Farofa (Serve 2 pessoas)",
      "name": "Frango a parmegiana",
      "price": 100
    },
    {
      "description": "Salmão, Arroz, Feijão, Salada e Farofa (Serve 2 pessoas)",
      "name": "Salmão",
      "price": 150
    }
  ]

  getAll() {
    this.menuService.getAll().subscribe(
      (res) => {
        console.log("Sucess");
        console.log(res);
        
        if (res.length === 0) {
          this.startCarga();
        }
        
        this.menus = res;

      },(error: HttpErrorResponse) => {
          console.log("Error");
          console.log(error.error);
        }
    );
  }

  startCarga() {
    console.log("Start carga");
    
    this.carga.forEach(rec => {
      this.createMenu(rec);
    });
  }
  
  createMenu(form: NewMenuForm) {
    this.menuService.newMenu(form).subscribe(
      (res) => {
        console.log(`Created`);
        console.log(res);       
        
      },(error: HttpErrorResponse) => {
          console.log("Error");
          console.log(error.error);
        }
    );
  }

}
