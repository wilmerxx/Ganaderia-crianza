
// area.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Area} from "../models/area.model";
import {Ganado} from "../models/ganado";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AreaService {
  selectedArea: Area;
  areas: Area[] = [];

  constructor(private http: HttpClient) {
    this.selectedArea = new Area();
  }

  readonly URL_API = environment.baseUrl + '/areas';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';

  getAreas(): Observable<Area[]> {
    return this.http.get<Area[]>(this.URL_API + '/estados/Activo');
  }
  postArea(area: Area): Observable<Area> {
    return this.http.post<Area>(this.URL_API, area);
  }
  putArea(areaId: string, area: Area): Observable<any> {
    const areaUrl = `${this.URL_API}/${areaId}`;
    return this.http.put(areaUrl, area).pipe(catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }

  deleteArea(areaId: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${areaId}`);
  }

}
