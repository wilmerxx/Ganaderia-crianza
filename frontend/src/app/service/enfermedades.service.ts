import { Injectable } from '@angular/core';
import {Ganado} from "../models/ganado";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Medicina} from "../models/medicina.model";
import {Enfermedad  } from "../models/enfermedades.model";
import {environment} from "../../environments/environment";
import {Reproduccion} from "../models/reproduccion.model";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class EnfermedadesService {

  selectedEnfermedades: Enfermedad;
  enfermedades!: Enfermedad[];
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.selectedEnfermedades = new Enfermedad();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  readonly URL_API = environment.baseUrl + '/controlEnfermedades';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';


  getEnfermedad(): Observable<Enfermedad[]> {
    return this.http.get<Enfermedad[]>(this.URL_API+'/estados/Activo', {headers: this.headers});
  }
  getEnfermedadID(id: string): Observable<any> {
    return this.http.get<Enfermedad>(this.URL_API + '/' + id,{headers:this.headers}).pipe(
        catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }
  postEnfermedad(enfermedades: Enfermedad): Observable<any> {
    return this.http.post<Enfermedad>(this.URL_API, enfermedades, {headers: this.headers});
  }

  putEnfermedades(enfermedades: Enfermedad): Observable<any> {
    return this.http.put<Enfermedad>(this.URL_API, enfermedades, {headers: this.headers});
  }


  deleteEnfermedades(control_id: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${control_id}`, {headers: this.headers});
  }
}
