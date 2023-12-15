import {Component, OnInit} from '@angular/core';
import * as Chartist from 'chartist';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor() { }
  ngOnInit(): void {
    this.createChart();
  }

  createChart() {
    const dataDailySalesChart: any = {
      labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
      series: [
        [12, 17, 7, 17, 23, 18, 38]
      ]
    };

    const optionsDailySalesChart: any = {
      lineSmooth: Chartist.Interpolation.cardinal({
        tension: 0
      }),
      low: 0,
      high: 50,
      chartPadding: { top: 0, right: 0, bottom: 0, left: 0},
    }

    const dailySalesChart = new Chartist.LineChart('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

  }


}
