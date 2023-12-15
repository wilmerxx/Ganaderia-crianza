import { Component, OnInit } from '@angular/core';
import { ServicioService } from '../servicio.service';


@Component({
  selector: 'ganado-registro',
  templateUrl: './ganado-registro.component.html',
  styleUrls: ['./ganado-registro.component.css']
})
export class GanadoRegistroComponent implements OnInit {
  ganados: any = []; // array to store all ganados
  constructor(private  servicioService: ServicioService) {}

  // lifecycle hook to fetch initial data

  ngOnInit(): void {
    this.getGanados();
  }

  // traer lista de ganados
  getGanados(): void {
    this.servicioService.getGanados().subscribe(ganados => {
      this.ganados = ganados;
      console.log("hola");
    });
  }


}
