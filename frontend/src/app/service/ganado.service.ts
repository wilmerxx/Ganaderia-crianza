import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ganado } from '../models/ganado';
import {Area} from "../models/area.model";
@Injectable({
  providedIn: 'root'
})
export class GanadoService {
  selectedGanado: Ganado;
  ganados: Ganado[] = [];
  ganadosVaca: Ganado[] = [];
  ganadosToro: Ganado[] = [];
  ganadosTernero: Ganado[] = [];
  totalVacas: number = 0;
  constructor(private http: HttpClient) {
    this.selectedGanado = new Ganado();
  }

  readonly URL_API = 'http://localhost:8080/api/ganados';
  getGanados(): Observable<Ganado[]> {
    return this.http.get<Ganado[]>(this.URL_API);
  }

  postGanado(ganado: Ganado): Observable<any> {
    return this.http.post(this.URL_API, ganado);
  }

  putGanado(ganado: Ganado): Observable<any> {
    return this.http.put(this.URL_API ,ganado);
  }

  //obtener ganado por id
  getGanadoID(id: string): Observable<any>{
    return this.http.get(this.URL_API + '/' + id);
  }

  deleteGanado(id: string): Observable<any> {
    return this.http.delete(this.URL_API + '/' + id);
  }

  getGanadosTipo(tipo: string): Observable<any> {
    return this.http.get(this.URL_API + '/tipo/' + tipo);
  }

  //obtener ganado por
  getGanadoTipo(tipo: string){
    this.getGanadosTipo(tipo).subscribe((res) =>{
      this.ganados = res as Ganado[];
      if(tipo == 'Vaca'){
        this.ganadosVaca = this.ganados;
        console.log(this.ganadosVaca);
        this.totalVacas = this.ganadosVaca.length;
      }else if(tipo == 'Toro'){
        this.ganadosToro = this.ganados;
        console.log(this.ganadosToro);
      }else if(tipo == 'Ternero'){
        this.ganadosTernero = this.ganados;
        console.log(this.ganadosTernero);
      }
    });
  }

}
