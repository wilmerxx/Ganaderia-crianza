import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ganado } from '../models/ganado';
import {Area} from "../models/area.model";
import {environment} from "../../environments/environment";
@Injectable({
  providedIn: 'root'
})
export class GanadoService {
  [x: string]: any;
  selectedGanado: Ganado;
  ganados: Ganado[] = [];
  ganadosVaca: Ganado[] = [];
  ganadosToro: Ganado[] = [];
  ganadosTernero: Ganado[] = [];
  totalVacas: number = 0;
  constructor(private http: HttpClient) {
    this.selectedGanado = new Ganado();
  }

  readonly URL_API = environment.baseUrl + '/ganados';
  // @ts-ignore

  getGanados(): Observable<Ganado[]> {
    try {
      return this.http.get<Ganado[]>(this.URL_API);
    }catch (e) {
      console.log(e);
    }
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
      if(tipo == 'Vaca'){
        this.ganadosVaca = res as Ganado[];
      }else if(tipo == 'Toro'){
        this.ganadosToro = res as Ganado[];
      }else if(tipo == 'Ternero'){
        this.ganadosTernero = res as Ganado[];
      }
    });
  }

  //calcular edad del ganado en meses
  calcularEdad(fechaNacimiento: string): number{
    let fechaActual = new Date();
    let fechaNac = new Date(fechaNacimiento);
    let edad = fechaActual.getMonth() - fechaNac.getMonth() + (12 * (fechaActual.getFullYear() - fechaNac.getFullYear()));
    return edad;
  }

  busquedaGanado(busqueda: string): Observable<any>{
    return this.http.get(this.URL_API + '/nombre/' + busqueda);
  }

}
