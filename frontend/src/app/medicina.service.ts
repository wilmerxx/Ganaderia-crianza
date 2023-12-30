
import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Medicina} from "./models/medicina.model";
import { map, catchError } from 'rxjs/operators';
import {error} from "@angular/compiler-cli/src/transformers/util";


@Injectable({
  providedIn: 'root'
})
export class MedicinaService {
  selectedMedicina: Medicina;
  medicina!: Medicina[];

  readonly URL_API = "http://localhost:8080/api/medicina";
  readonly URL_GANADO_API = "http://localhost:8080/api/ganados";

  constructor(private http: HttpClient) {
    this.selectedMedicina = new Medicina();
  }

  getMedicinas(): Observable<Medicina[]> {
    return this.http.get<Medicina[]>(this.URL_API);
  }

  postMedicina(medicina: Medicina): Observable<any> {
    return this.http.post(this.URL_API, medicina);
  }
    getGanadoNombre(ganadoId: string): Observable<string> {
        const url = `${this.URL_GANADO_API}/${ganadoId}`;
        return this.http.get<any>(url).pipe(
            map((ganado: any) => ganado.nombre_ganado),
            catchError(this.handleError)
        );
    }


    private handleError(error: HttpErrorResponse) {
        console.error('Error en la solicitud:', error);
        return throwError('Error en la solicitud. Por favor, inténtalo de nuevo más tarde.');
    }

  putMedicina(medicina: Medicina): Observable<any> {
    return this.http.put(this.URL_API + `/${medicina.medicinaId}`, medicina);
  }

  deleteMedicina(medicinaId: string): Observable<any> {
    return this.http.delete(this.URL_API + `/${medicinaId}`);
  }
}
