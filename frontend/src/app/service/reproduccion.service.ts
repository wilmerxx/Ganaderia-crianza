import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Reproduccion} from "../models/reproduccion.model";
import {environment} from "../../environments/environment";
import {catchError} from "rxjs/operators";
import {Ganado} from "../models/ganado";

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

  putReproduccion(reproduccion: Reproduccion): Observable<any> {
    return this.http.put<Reproduccion>(this.URL_API ,reproduccion);
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

  deleteReproduccion(reproduccion_id: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${reproduccion_id}`);
  }
}
