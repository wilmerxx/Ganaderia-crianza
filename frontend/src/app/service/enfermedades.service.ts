import { Injectable } from '@angular/core';
import {Ganado} from "../models/ganado";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Medicina} from "../models/medicina.model";
import {Enfermedad  } from "../models/enfermedades.model";

@Injectable({
  providedIn: 'root'
})
export class EnfermedadesService {

  selectedEnfermedades: Enfermedad;
  enfermedades!: Enfermedad[];
  constructor(private http: HttpClient) {
    this.selectedEnfermedades = new Enfermedad();
  }

  readonly URL_API = 'http://localhost:8080/api/controlEnfermedades';

  getEnfermedad(): Observable<Enfermedad[]> {
    return this.http.get<Enfermedad[]>(this.URL_API);
  }

  postEnfermedad(enfermedades: Enfermedad): Observable<any> {
    return this.http.post(this.URL_API, enfermedades);
  }

  putEnfermedades(enfermedades: Enfermedad): Observable<any> {
    return this.http.put(this.URL_API, enfermedades);
  }


  deleteEnfermedades(id: string | undefined): Observable<any> {
    return this.http.delete(this.URL_API + '/' + id);
  }
}
