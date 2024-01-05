
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Alimentacion} from "../models/alimentacion.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AlimentacionService {
  selectedAlimentacion: Alimentacion;
  alimentacion!: Alimentacion[];

  constructor(private http: HttpClient) {
    this.selectedAlimentacion = new Alimentacion();
  }

  readonly URL_API = environment.baseUrl + '/alimentacion';

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
