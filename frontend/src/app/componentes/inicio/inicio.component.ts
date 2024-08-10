import {Component, OnInit, AfterViewInit, ViewChild, ElementRef} from '@angular/core';
import * as Chartist from 'chartist';
import { GanadoService } from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {Chart, ChartDataset, ChartOptions, ChartType, Color, Colors, plugins} from "chart.js";
import * as Highcharts from 'highcharts/highstock';
import * as ngBootstrap from '@ng-bootstrap/ng-bootstrap';

import {ServicioService} from "../../service/servicio.service";
import {AlimentacionService} from "../../service/alimentacion.service";
@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  Highcharts: typeof Highcharts = Highcharts;
  chartOptions!: Highcharts.Options;
  public totalTerneros: number = 0;
  public totalGanado: number = 0;
  public doughnutChartData:ChartDataset[] = [];
  public barChartDatasets: ChartDataset[] = [];
  public totalMachos: number = 0;
  public totalHembras: number = 0;
  chart!: Highcharts.Options;
  datosMacho: any[] = [];
  datosHembra: any[] = [];
  chatpie!: Highcharts.Options;


  constructor(private ganadoService: GanadoService, private AlimentacionService: AlimentacionService) {

  }

  @ViewChild('lineChart') private lineChart!: ElementRef;
  ngOnInit(): void {
    this.barChartDatos();
    this.renderLineChart();
    this.graficaBarPorSexoGanado();
    this.graficaPastelGanado();
    console.log(localStorage.getItem('password'));
    console.log(localStorage.getItem('token'))
    console.log(localStorage.getItem('username'))

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

  graficaPastelGanado(event?: Event) {
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

      this.chatpie = {
        chart: {
          type: 'pie'
        },
        title: {
          text: 'Ganado por tipo'
        },
        series: [{
          type: 'pie',
          name: 'Cantidad',
          data: [
            ['Vacas', totalVacas],
            ['Toros', totalToros],
            ['Terneros', totalTerneros],
            ['Terneras', totalTerneras],
            ['Vaquillas', totalVaquillas],
            ['Torillos', totalTorillos]
          ]
        }]
      }
      Highcharts.chart('pieChart', this.chatpie);
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

  //grafica para ver las cantidades de alimento por animal
  graficaSeriesPorAlimentacionAnimal(event?: Event) {
    event?.preventDefault();
    let cantidadAlimento: number = 0;

    this.AlimentacionService.getAlimentacion().subscribe((res) => {
      res.forEach((alimentacion) => {
        cantidadAlimento += alimentacion.cantidad_suplemento;
      });
      this.chart = {
        chart: {
          type: 'bar'
        },
        title: {
          text: 'Cantidad de alimento por animal'
        },
        xAxis: {
          categories: ['Vacas', 'Toros', 'Terneros', 'Terneras', 'Vaquillas', 'Torillos']
        },
        yAxis: {
          title: {
            text: 'Cantidad de alimento'
          }
        },
        series: [{
          type: 'bar',
          name: 'Cantidad',
          data: [cantidadAlimento, cantidadAlimento, cantidadAlimento, cantidadAlimento, cantidadAlimento, cantidadAlimento],
          color: '#66e135'
        }]
      };
      Highcharts.chart('barChartAlimento', this.chart);
    });
  }

}

