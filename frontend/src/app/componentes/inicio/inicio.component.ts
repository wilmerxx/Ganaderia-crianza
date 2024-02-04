import {Component, OnInit, AfterViewInit, ViewChild, ElementRef} from '@angular/core';
import * as Chartist from 'chartist';
import { GanadoService } from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {Chart, ChartDataset, ChartOptions, ChartType, Color, Colors, plugins} from "chart.js";
import * as Highcharts from 'highcharts/highstock';
import * as ngBootstrap from '@ng-bootstrap/ng-bootstrap';

import {ServicioService} from "../../service/servicio.service";
@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  Highcharts: typeof Highcharts = Highcharts;
  chartOptions!: Highcharts.Options;
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
  public barChartDatasets: ChartDataset[] = [];
  public totalMachos: number = 0;
  public totalHembras: number = 0;
  chart!: Highcharts.Options;
  datosMacho: any[] = [];
  datosHembra: any[] = [];



  constructor(private ganadoService: GanadoService, private servicioService: ServicioService) {

  }

  @ViewChild('lineChart') private lineChart!: ElementRef;
  ngOnInit(): void {
    this.ganadoService.getGanadoTipo('Toro');
    this.ganadoService.getGanadoTipo('Ternero');
    this.ganadoService.getGanadoTipo('Vaca');
    this.datosDoughnut();
    this.barChartDatos();
    this.renderLineChart();
    this.graficaBarPorSexoGanado();

  }




  //datos de grafica de pastel
  datosDoughnut(){
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


  //grafica de barras con highcharts
  graficaBarPorSexoGanado(event?: Event) {
    event?.preventDefault();
    let totalVacas:  number = 0;
    let totalToros:  number = 0;
    let totalTerneros:  number = 0;
    let totalTerneras:  number = 0;
    let totalVaquillas:  number = 0;
    let totalTorillos:  number = 0;
    this.ganadoService.getGanados().subscribe((res) => {
      res.forEach((ganado) => {
       if(ganado.tipo == 'Vaca'){
         totalVacas++;
        }else if(ganado.tipo == 'Toro'){
         totalToros++;
       }else if(ganado.tipo == 'Ternero'){
         totalTerneros++;
       }else if(ganado.tipo == 'Ternera'){
         totalTerneras++;
       }else if(ganado.tipo == 'Vaquilla'){
         totalVaquillas++;
       }else if(ganado.tipo == 'Torillo'){
         totalTorillos++;
       }

      });

      this.chart = {
        chart: {
          type: 'bar'
        },
        title: {
          text: 'Ganado por sexo'
        },
        xAxis: {
          categories: ['Adultos', 'Jovenes','Crias']
        },
        yAxis: {
          title: {
            text: 'Cantidad de ganado por sexo'
          }
        },
        series: [{
          type: 'bar',
          name: 'Machos',
          data: [totalToros, totalTorillos, totalTerneros],
          color: '#66e135'
        }, {
          type: 'bar',
          name: 'Hembras',
          data: [totalVacas, totalVaquillas, totalTerneras],
          color: '#c378dc'
        }]
      };
      Highcharts.chart('barChart', this.chart);
    });
  }



  //graficar el peso del animal por fecha seleccionan el tipo de animal
  renderLineChart() {
    this.ganadoService.getGanados().subscribe((res) => {
      res.forEach((ganado) => {
        //ordenar por fecha
        for (let i = 0; i < res.length; i++) {
          for (let j = 0; j < res.length - 1; j++) {
            if (res[j].fechaNacimiento > res[j + 1].fechaNacimiento) {
              let temp = res[j];
              res[j] = res[j + 1];
              res[j + 1] = temp;
            }
          }
        }
        if (ganado.sexo == 'Macho') {
          this.datosMacho.push([new Date(ganado.fechaNacimiento).getTime(), ganado.edad]);
        } else {
          this.datosHembra.push([new Date(ganado.fechaNacimiento).getTime(), ganado.edad]);
        }
      });

      const options: Highcharts.Options = {
        chart: {
          type: 'line'
        },
        title: {
          text: 'Alimentación por fechas y Sexo'
        },
        xAxis: {
          type: 'datetime',
          title: {
            text: 'Fecha'
          }
        },
        yAxis: {
          title: {
            text: 'Cantidad de alimentación'
          }
        },
        series: [{
          type: 'line',
          name: 'Macho',
          data: this.datosMacho,
          color: '#0000FF'
        }, {
          type: 'line',
          name: 'Hembra',
          data: this.datosHembra,
          color: '#FF0000'
        }]
      }
      Highcharts.chart('lineChart', options);
    });
  }

}

