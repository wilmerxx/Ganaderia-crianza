import { Injectable } from '@angular/core';
import {Inventario} from "../models/inventario";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Producto} from "../models/producto";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InventarioMongoService {
selectedInventario: Inventario;

inventario: Inventario[]= [];
headers: HttpHeaders;
  constructor(
      private http: HttpClient,
) {
    this.selectedInventario = new Inventario();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

    readonly URL_API = environment.mongoUrl + '/inventario';

  getInventario(): Observable<Inventario[]>{
    return this.http.get<Inventario[]>(this.URL_API, {headers: this.headers});
  }
  postInventario(inventario: Inventario): Observable<Inventario>{
    return this.http.post<Inventario>(this.URL_API, inventario, {headers: this.headers});
  }
  getInventarioID(id: string): Observable<Inventario>{
    return this.http.get<Inventario>(`${this.URL_API}/${id}`, {headers: this.headers});
  }
  deleteInventario(id: number | undefined): Observable<any>{
    return this.http.delete(`${this.URL_API}/${id}`, {headers: this.headers});
  }

}
