import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Reproduccion} from "../models/reproduccion.model";
import {environment} from "../../environments/environment";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ReproduccionService {
  selectedReproduccion:Reproduccion;
  reproducciones: Reproduccion[] = [];

  constructor(private http: HttpClient) {
    this.selectedReproduccion = new Reproduccion();
  }

  readonly URL_API = environment.baseUrl+'/reproducciones';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';

  getReproduccion(): Observable<Reproduccion[]> {
    return this.http.get<Reproduccion[]>(this.URL_API + '/estados/Activo');
  }

  postReproduccion(reproduccion: Reproduccion): Observable<Reproduccion> {
    return this.http.post<Reproduccion>(this.URL_API, reproduccion);
  }

  putReproduccion(reproducciones: Reproduccion){
    return this.http.put(this.URL_API + `/${reproducciones.reproduccion_id}`, reproducciones);
  }
  getReproduccionesID(id: string): Observable<any> {
    return this.http.get<Reproduccion>(this.URL_API + '/' + id).pipe(
      catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }

  deleteReproduccion(reproduccion_id: string): Observable<any> {
    return this.http.delete(`${this.URL_API}/${reproduccion_id}`);
  }
}
