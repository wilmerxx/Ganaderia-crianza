
// area.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Area} from "./models/area.model";

@Injectable({
  providedIn: 'root'
})
export class AreaService {
  selectedArea: Area;
  areas!: Area[];

  readonly URL_API = "http://localhost:8080/api/areas"; // Ajusta la URL según tu API

  constructor(private http: HttpClient) {
    this.selectedArea = new Area();
  }

  getAreas(): Observable<Area[]> {
    return this.http.get<Area[]>(this.URL_API);
  }

  postArea(area: Area): Observable<any> {
    return this.http.post(this.URL_API, area);
  }

  putArea(area: Area): Observable<any> {
    return this.http.put(this.URL_API + `/${area.areaId}`, area);
  }

  deleteArea(areaId: string): Observable<any> {
    return this.http.delete(this.URL_API + `/${areaId}`);
  }
}
