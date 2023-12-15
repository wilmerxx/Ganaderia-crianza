import {HttpClient} from "@angular/common/http";
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ServicioService {


  constructor(private http: HttpClient) {}
  private url = 'http://localhost:8080/api/animal';

  public getGanados(){
    return this.http.get(this.url);
  }


  deleteGanado(id: number | undefined): Observable<void> {
    return this.http.delete<void>('/ganados/' + id);
  }

}
