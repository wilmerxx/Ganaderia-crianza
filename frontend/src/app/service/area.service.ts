
// area.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
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
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.selectedArea = new Area();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  readonly URL_API = environment.baseUrl + '/areas';
  readonly URL_GANADO_API = environment.baseUrl + '/ganados';

  getAreas(): Observable<Area[]> {
    return this.http.get<Area[]>(this.URL_API + '/estados/Activo', {headers: this.headers});
  }
  postArea(area: Area): Observable<Area> {
    return this.http.post<Area>(this.URL_API, area, {headers: this.headers});
  }
  putArea(area: Area): Observable<any> {
    return this.http.put<Area>(this.URL_API ,area, {headers: this.headers});
  }
  getAreaID(id: string): Observable<any>{
    return this.http.get(this.URL_API + '/' + id, {headers: this.headers});
  }

  private handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError('Algo salió mal');
  }

  deleteArea(areaId: number | undefined): Observable<any> {
    return this.http.delete(`${this.URL_API}/${areaId}`, {headers: this.headers});
  }

}
