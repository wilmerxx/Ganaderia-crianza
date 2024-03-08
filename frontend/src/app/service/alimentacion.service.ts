
import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Alimentacion} from "../models/alimentacion.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AlimentacionService {
  selectedAlimentacion: Alimentacion;
  alimentacion!: Alimentacion[];
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.selectedAlimentacion = new Alimentacion();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  readonly URL_API = environment.baseUrl + '/alimentacion';


  getAlimentacion(): Observable<Alimentacion[]> {
    return this.http.get<Alimentacion[]>(this.URL_API + '/estado/Activo', {headers: this.headers});
  }

  postAlimentacion(alimentacion: Alimentacion): Observable<any> {
    return this.http.post<Alimentacion>(this.URL_API, alimentacion);
  }

  putAlimentacion(alimentacion: Alimentacion): Observable<any> {
    return this.http.put<Alimentacion>(this.URL_API, alimentacion);
  }

  getAlimentacionID(id: string): Observable<Alimentacion> {
    return this.http.get<Alimentacion>(`${this.URL_API}/${id}`);
  }

  deleteAlimentacion(alimentacion_id: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${alimentacion_id}`);
  }
}
