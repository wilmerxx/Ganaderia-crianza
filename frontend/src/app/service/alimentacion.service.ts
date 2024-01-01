
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Alimentacion} from "../models/alimentacion.model";

@Injectable({
  providedIn: 'root'
})
export class AlimentacionService {
  selectedAlimentacion: Alimentacion;
  alimentacion!: Alimentacion[];

  readonly URL_API = "http://localhost:8080/api/alimentacion"; // Ajusta la URL según tu API

  constructor(private http: HttpClient) {
    this.selectedAlimentacion = new Alimentacion();
  }

  getAlimentacion(): Observable<Alimentacion[]> {
    return this.http.get<Alimentacion[]>(this.URL_API);
  }

  postAlimentacion(alimentacion: Alimentacion): Observable<any> {
    return this.http.post(this.URL_API, alimentacion);
  }

  putAlimentacion(alimentacion: Alimentacion): Observable<any> {
    return this.http.put(this.URL_API + `/${alimentacion.alimentacion_id}`, alimentacion);
  }

  deleteAlimentacion(alimentacion_id: string): Observable<any> {
    return this.http.delete(this.URL_API + `/${alimentacion_id}`);
  }
}
