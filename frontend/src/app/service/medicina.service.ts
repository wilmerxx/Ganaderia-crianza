import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { Medicina } from "../models/medicina.model";
import { Ganado } from "../models/ganado";
import { map, catchError, switchMap } from 'rxjs/operators';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MedicinaService {
  selectedMedicina: Medicina;
  medicinas: Medicina[] = [];

  constructor(private http: HttpClient) {
    this.selectedMedicina = new Medicina();
  }

  readonly URL_API = environment.baseUrl + '/medicina';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';

  getMedicinas(): Observable<Medicina[]> {
    return this.http.get<Medicina[]>(this.URL_API+ '/estados/Activo');
  }

  postMedicina(medicina: Medicina): Observable<Medicina> {
    return this.http.post<Medicina>(this.URL_API, medicina);
  }

  putMedicina(medicina: Medicina): Observable<Medicina> {
    return this.http.put<Medicina>(this.URL_API, medicina);
  }

  getMedicinaID(id: string): Observable<Medicina> {
    return this.http.get<Medicina>(`${this.URL_API}/${id}`).pipe(
        catchError(this.handleError)
    );
  }

  private getGanadoID(ganadoId: string): Observable<Ganado> {
    return this.http.get<Ganado>(this.URL_GANADO_API + '/' + ganadoId);
  }

  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }

  deleteMedicina(medicina_id: string): Observable<any> {
    return this.http.delete(`${this.URL_API}/${medicina_id}`);
  }
}
