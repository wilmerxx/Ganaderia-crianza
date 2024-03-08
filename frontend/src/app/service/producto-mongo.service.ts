import { Injectable } from '@angular/core';
import {Producto} from "../models/producto";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductoMongoService {
  selectedProducto: Producto;
  productos: Producto[] = [];
  headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.selectedProducto = new Producto();
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }
  readonly URL_API = environment.mongoUrl + '/producto';

  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.URL_API, {headers: this.headers});
  }
  postProducto(producto: Producto): Observable<Producto>{
    return this.http.post<Producto>(this.URL_API, producto, {headers: this.headers});
  }
  putProducto(producto: Producto): Observable<Producto>{
    return this.http.put<Producto>(this.URL_API, producto, {headers: this.headers});
  }
  getProductoID(id: string): Observable<Producto>{
    return this.http.get<Producto>(`${this.URL_API}/${id}`, {headers: this.headers});
  }
  deleteProducto(id: number | undefined): Observable<any>{
    return this.http.delete(`${this.URL_API}/${id}`, {headers: this.headers});
  }
}
