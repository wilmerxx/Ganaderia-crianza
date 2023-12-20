
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Medicina} from "./models/medicina.model";

@Injectable({
  providedIn: 'root'
})
export class MedicinaService {
  selectedMedicina: Medicina;
  medicina!: Medicina[];

  readonly URL_API = "http://localhost:8080/api/medicina"; // Ajusta la URL según tu API

  constructor(private http: HttpClient) {
    this.selectedMedicina = new Medicina();
  }

  getMedicinas(): Observable<Medicina[]> {
    return this.http.get<Medicina[]>(this.URL_API);
  }

  postMedicina(medicina: Medicina): Observable<any> {
    return this.http.post(this.URL_API, medicina);
  }

  putMedicina(medicina: Medicina): Observable<any> {
    return this.http.put(this.URL_API + `/${medicina.medicinaId}`, medicina);
  }

  deleteMedicina(medicinaId: string): Observable<any> {
    return this.http.delete(this.URL_API + `/${medicinaId}`);
  }
}
