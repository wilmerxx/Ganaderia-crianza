import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
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
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.selectedMedicina = new Medicina();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  readonly URL_API = environment.baseUrl + '/medicina';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';

  getMedicinas(): Observable<Medicina[]> {
    return this.http.get<Medicina[]>(this.URL_API+ '/estados/Activo', {headers: this.headers});
  }

  postMedicina(medicina: Medicina): Observable<Medicina> {
    return this.http.post<Medicina>(this.URL_API, medicina, {headers: this.headers});
  }

  putMedicina(medicina: Medicina): Observable<any> {
    return this.http.put<Medicina>(this.URL_API, medicina, {headers: this.headers});
  }

  getMedicinaID(id: string): Observable<Medicina> {
    return this.http.get<Medicina>(`${this.URL_API}/${id}`, {headers:this.headers}).pipe(
        catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }

  deleteMedicina(medicina_id: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${medicina_id}`, {headers: this.headers});
  }
}
