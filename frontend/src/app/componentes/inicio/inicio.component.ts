import { Component, OnInit } from '@angular/core';
import * as Chartist from 'chartist';
import { GanadoService } from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {Chart, ChartDataset, ChartOptions, ChartType, Color, Colors} from "chart.js";
import {baseColors} from "ng2-charts";
import * as colorette from "colorette";
import {ColorsPluginOptions} from "chart.js/dist/plugins/plugin.colors";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  //graficar los ganados por tipo
  public ganados: Ganado[] = [];
  public vacas: Ganado[] = [];
  public toros: Ganado[] = [];
  public terneros: Ganado[] = [];
  //public doughnutChartData:ChartDataset[] = [];

  constructor(private ganadoService: GanadoService) {  }


  ngOnInit(): void {
    this.ganadoService.getGanadoTipo('Toro');
    this.ganadoService.getGanadoTipo('Ternero');
    this.ganadoService.getGanadoTipo('Vaca');
    this.ganadoService.totalVacas;
    console.log(this.vacas.length);

  }


  datos(){

  }

  public doughnutChartLabels:string[] = ['Vacas', 'Toros', 'Terneros'];

  public doughnutChartData: ChartDataset[] = [
    {
      data: [34,456, 456],
      backgroundColor: [
        '#FF6384',
        '#36A2EB',
        '#FFCE56'
      ],
      hoverBackgroundColor: [
        '#FF6384',
        '#36A2EB',
        '#FFCE56'
      ]
    }
    ];

  public doughnutChartType:ChartType = 'doughnut';

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }


}
