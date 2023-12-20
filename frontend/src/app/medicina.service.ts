import { Injectable } from '@angular/core';
import {Ganado} from "./models/ganado";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Medicina} from "./models/medicina.model";

@Injectable({
  providedIn: 'root'
})
export class MedicinaService {

  selectedMedicia: Medicina;
  medicina!: Medicina[];
  constructor(private http: HttpClient) {
    this.selectedMedicia = new Medicina();
  }

  readonly URL_API = 'http://localhost:8080/api/medicina';
  getMedicna(): Observable<Medicina[]> {
    return this.http.get<Medicina[]>(this.URL_API);
  }

  postMedicina(medicina: Medicina): Observable<any> {
    return this.http.post(this.URL_API, medicina);
  }

}
