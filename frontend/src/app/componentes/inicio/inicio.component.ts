import { Component, OnInit } from '@angular/core';
import * as Chartist from 'chartist';
import { GanadoService } from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {Chart, ChartDataset, ChartOptions, ChartType, Color, Colors, plugins} from "chart.js";
<<<<<<< HEAD
import {baseColors} from "ng2-charts";
import * as colorette from "colorette";
import {ColorsPluginOptions} from "chart.js/dist/plugins/plugin.colors";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";
import {Observable} from "rxjs";
=======
>>>>>>> Desarrollo
import {ServicioService} from "../../service/servicio.service";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  //graficar los ganados por tipo
  private ganados: Ganado[] = [];
  public vacas: Ganado[] = [];
  public toros: Ganado[] = [];
  public terneros: Ganado[] = [];
  public totalVacas: number = 0;
  public totalToros: number = 0;
  public totalTerneros: number = 0;
  public totalGanado: number = 0;
  public doughnutChartData:ChartDataset[] = [];
<<<<<<< HEAD
=======
  public barChartDatasets: ChartDataset[] = [];
  public totalMachos: number = 0;
  public totalHembras: number = 0;
>>>>>>> Desarrollo

  constructor(private ganadoService: GanadoService, private servicioService: ServicioService) {

  }
  ngOnInit(): void {
    this.ganadoService.getGanadoTipo('Toro');
    this.ganadoService.getGanadoTipo('Ternero');
    this.ganadoService.getGanadoTipo('Vaca');
    this.datosDoughnut();
<<<<<<< HEAD
=======
    this.barChartDatos();
>>>>>>> Desarrollo
  }


  //datos de grafica de pastel
  datosDoughnut(){
<<<<<<< HEAD
    this.ganadoService.getGanados().subscribe((res) =>{
      this.ganados = res as Ganado[];
      this.ganadoService.ganados = this.ganados;
      this.vacas = this.ganadoService.ganadosVaca;
      this.toros = this.ganadoService.ganadosToro;
      this.terneros = this.ganadoService.ganadosTernero;
      this.totalVacas = this.vacas.length;
      this.totalToros = this.toros.length;
      this.totalTerneros = this.terneros.length;
      this.totalGanado = this.totalVacas + this.totalToros + this.totalTerneros;
      this.doughnutChartData = [
        {
          label: 'Cantidad por tipo', data: [this.totalVacas,this.totalToros, this.totalTerneros],
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
    });
  }

  //grafica de barras
  public barChartOptions: ChartOptions = {
    hover: {
      mode: 'point',
      intersect: true
    },
    animation: {
      duration: 3000,
      easing: 'easeOutQuart'
    },
    scales: {
      x: {
        grid: {
          display: false,
        }
      },
      y: {
        grid: {
          display: true,
        }
      }
    },
    responsive: true,
  };
  public barChartLabels: string[] = ['Vacas', 'Toros', 'Terneros'];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

=======

    this.ganadoService.getGanados().subscribe((res) =>{
      this.ganados = res as Ganado[];
      this.ganadoService.ganados = this.ganados;
      this.vacas = this.ganadoService.ganadosVaca;
      this.toros = this.ganadoService.ganadosToro;
      this.terneros = this.ganadoService.ganadosTernero;
      this.totalVacas = this.vacas.length;
      this.totalToros = this.toros.length;
      this.totalTerneros = this.terneros.length;
      this.totalGanado = this.totalVacas + this.totalToros + this.totalTerneros;
      this.doughnutChartData = [
        {
          label: 'Cantidad por tipo', data: [this.totalVacas,this.totalToros, this.totalTerneros],
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
    });
  }

  barChartDatos(){
    this.ganadoService.getGanados().forEach((res) =>{
        //condicion si es macho o hembra
        res.forEach((ganado) =>{
          if(ganado.sexo == 'Macho'){
            this.totalMachos++;
          }else{
            this.totalHembras++;
          }
        });

      this.barChartDatasets = [
        {
          label: 'Sexo', data: [this.totalMachos, this.totalHembras],
          backgroundColor: [
            '#38d93b',
          ],
          hoverBackgroundColor: [
            '#3ca140',
          ]
        },
        {
          label: 'Tipo', data: [this.totalHembras, this.totalTerneros],
          backgroundColor: [
            'rgba(227,47,115,0.99)',
          ],
          hoverBackgroundColor: [
            '#b71256',
          ]
        }
      ];
    });
  }

  //grafica de barras
  public barChartOptions: ChartOptions = {
    hover: {
      mode: 'point',
      intersect: true
    },
    animation: {
      duration: 3000,
      easing: 'easeOutQuart'
    },
    scales: {
      x: {
        grid: {
          display: false,
        }
      },
      y: {
        grid: {
          display: true,
        }
      }
    },
    responsive: true,
  };
  public barChartLabels: string[] = ['Machos', 'Hembras'];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

>>>>>>> Desarrollo

  //graficar los cantidad de padres por tipo
  public barChartData: ChartDataset[] = [
    { data: [this.totalVacas, this.totalToros, this.totalTerneros], label: 'Cantidad por tipo' }
  ];


  //grafica de pastel
  public doughnutChartLabels:string[] = ['Vacas', 'Toros', 'Terneros'];
  public doughnutChartType:ChartType = 'doughnut';

  // events
  public chartClicked(e:any):void {
    //console.log(e);
  }

  public chartHovered(e:any):void {
    //console.log(e);
  }



  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;


  get single() {
    return this.servicioService.countryData;
  }

  onRandomData() {
    this.servicioService.randomData();
  }

  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }


}
