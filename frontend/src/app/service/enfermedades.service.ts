import { Injectable } from '@angular/core';
import {Ganado} from "../models/ganado";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
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
  constructor(private http: HttpClient) {
    this.selectedEnfermedades = new Enfermedad();
  }

  readonly URL_API = environment.baseUrl + '/controlEnfermedades';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';


  getEnfermedad(): Observable<Enfermedad[]> {
    return this.http.get<Enfermedad[]>(this.URL_API+'/estados/Activo');
  }
  getEnfermedadID(id: string): Observable<any> {
    return this.http.get<Enfermedad>(this.URL_API + '/' + id).pipe(
        catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }
  postEnfermedad(enfermedades: Enfermedad): Observable<any> {
    return this.http.post<Enfermedad>(this.URL_API, enfermedades);
  }

  putEnfermedades(enfermedades: Enfermedad): Observable<any> {
    return this.http.put<Enfermedad>(this.URL_API, enfermedades);
  }


  deleteEnfermedades(control_id: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${control_id}`);
  }
}
