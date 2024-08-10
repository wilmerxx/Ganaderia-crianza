import { Component, OnInit } from '@angular/core';
import {ServicioService} from "../service/servicio.service";

@Component({
  selector: 'app-navegacion',
  templateUrl: './navegacion.component.html',
  styleUrls: ['./navegacion.component.css']
})
export class NavegacionComponent implements OnInit {

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

  salir() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    this.servicio.logout();
  }
}
