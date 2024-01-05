
// area.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, Observable} from 'rxjs';
import {Area} from "../models/area.model";
import {Ganado} from "../models/ganado";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AreaService {
  selectedArea: Area;
  areas!: Area[];

  readonly URL_API = environment.baseUrl + '/area';

  constructor(private http: HttpClient) {
    this.selectedArea = new Area();
  }

  getAreas(): Observable<Area[]> {
    return this.http.get<Area[]>(this.URL_API);
  }
  postArea(area: Area): Observable<any> {
    return this.http.post(this.URL_API, area);
  }

  editar(area: Area): Observable<any> {
    const url = `${this.URL_API}/${area.areaId}`;

    return this.http.put(url, area).pipe(
      catchError((error) => {
        console.error('Error updating area:', error);
        throw error; // Rethrow the error to propagate it to the calling code
      })
    );
  }
  deleteArea(areaId: string): Observable<any> {
    return this.http.delete(this.URL_API + `/${areaId}`);
  }
}
