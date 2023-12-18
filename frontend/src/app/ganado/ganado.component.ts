import {Component, OnInit} from '@angular/core';
import * as Chartist from 'chartist';

@Component({
  selector: 'app-ganado',
  templateUrl: './ganado.component.html',
  styleUrls: ['./ganado.component.css']
})
export class  GanadoComponent{

  title = 'Dashboard';
  sidebarCollapsed = false;

  toggleSidebar() {
    this.sidebarCollapsed = !this.sidebarCollapsed;
  }  
  enlaceActivo: string = ''; // Inicializa la variable que representa el enlace activo

  // Método para cambiar el enlace activo
  cambiarEnlaceActivo(enlace: string) {
    this.enlaceActivo = enlace;
  }

}
