import {Component, OnInit, ViewChild} from '@angular/core';
import * as Chartist from 'chartist';
import {ServicioService} from "./service/servicio.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Dashboard';
  sidebarCollapsed = false;
  mainbar = false;
  constructor(private servicio:ServicioService) { }

  enlaceActivo: string = ''; // Inicializa la variable que representa el enlace activo

  ngOnInit(): void {
    this.sidebarCollapsed = false;
  }
  // Método para cambiar el enlace activo
  cambiarEnlaceActivo(enlace: string) {
    this.enlaceActivo = enlace;
  }


  closeSidebar() {
    this.sidebarCollapsed =  true;

  }

  openSidebar() {
    if (this.sidebarCollapsed) {
      this.sidebarCollapsed = false;
      this.mainbar = false;
    } else {
      this.sidebarCollapsed = true;
      this.mainbar = true;
    }
  }

}
