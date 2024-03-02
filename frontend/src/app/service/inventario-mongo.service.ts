import { Injectable } from '@angular/core';
import {Inventario} from "../models/inventario";
import {HttpClient} from "@angular/common/http";
import {Producto} from "../models/producto";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InventarioMongoService {
selectedInventario: Inventario;

inventario: Inventario[]= [];

  constructor(
      private http: HttpClient,
) {
    this.selectedInventario = new Inventario();
  }

  readonly URL_API = environment.mongoUrl + '/inventario';

  getInventario(): Observable<Inventario[]>{
    return this.http.get<Inventario[]>(this.URL_API);
  }
  postInventario(inventario: Inventario): Observable<Inventario>{
    return this.http.post<Inventario>(this.URL_API, inventario);
  }
  getInventarioID(id: string): Observable<Inventario>{
    return this.http.get<Inventario>(`${this.URL_API}/${id}`);
  }
  deleteInventario(id: number | undefined): Observable<any>{
    return this.http.delete(`${this.URL_API}/${id}`);
  }

}
