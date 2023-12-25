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
  ganados!: Ganado[];
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
}
