import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import * as Chartist from 'chartist';
import  {ServicioService} from './servicio.service';

@Component({
  selector: 'app-ganado',
  templateUrl: './ganado.component.html',
  styleUrls: ['./ganado.component.css']
})
export class  GanadoComponent implements OnInit{

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
