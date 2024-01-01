import { Injectable } from '@angular/core';
import {Area} from "../models/area.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Reproduccion} from "../models/reproduccion.model";
import {Ganado} from "../models/ganado";

@Injectable({
  providedIn: 'root'
})
export class ReproduccionService {
  selectedReproduccion:Reproduccion;
  reproducciones!: Reproduccion[];

  readonly URL_API = "http://localhost:8080/api/reproducciones"; // Ajusta la URL según tu API

  constructor(private http: HttpClient) {
    this.selectedReproduccion = new Reproduccion();
  }

  getReproduccion(): Observable<Reproduccion[]> {
    return this.http.get<Reproduccion[]>(this.URL_API);
  }

  postReproduccion(reproducciones: Reproduccion): Observable<any> {
    return this.http.post(this.URL_API, reproducciones);
  }


  putReproduccion(reproducciones: Reproduccion){
    return this.http.put(this.URL_API + `/${reproducciones.reproduccion_id}`, reproducciones);
  }


  deleteReproduccion(reproduccion_id: string): Observable<any> {
    return this.http.delete(this.URL_API + `/${reproduccion_id}`);
  }
}
