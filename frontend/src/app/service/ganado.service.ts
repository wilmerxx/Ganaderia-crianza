import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
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
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.selectedGanado = new Ganado();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }



  readonly URL_API = environment.baseUrl + '/ganados';

  getGanados(): Observable<Ganado[]> {
    return this.http.get<Ganado[]>(this.URL_API, { headers: this.headers });
  }

  getGanadosPaginacion(texto:string,page:number,size:number): Observable<any> {
    return this.http.get<Ganado[]>(`${this.URL_API}/nombre/${texto}/${page}/${size}`, { headers: this.headers });
  }

  postGanado(ganado: Ganado): Observable<Ganado> {
    return this.http.post<Ganado>(this.URL_API, ganado);
  }

  putGanado(ganado: Ganado): Observable<any> {
    return this.http.put(this.URL_API ,ganado);
  }


  //obtener ganado por id
  getGanadoID(id: string): Observable<any>{
    return this.http.get(this.URL_API + '/' + id);
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

  deleteGanado(ganado_id: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${ganado_id}`);
  }
}
