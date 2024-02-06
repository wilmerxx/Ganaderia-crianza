import { Injectable } from '@angular/core';
import {Producto} from "../models/producto";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductoMongoService {
  selectedProducto: Producto;
  productos: Producto[] = [];

  constructor(private http: HttpClient) {
    this.selectedProducto = new Producto();
  }
  readonly URL_API = environment.mongoUrl + '/producto';

  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.URL_API);
  }
  postProducto(producto: Producto): Observable<Producto>{
    return this.http.post<Producto>(this.URL_API, producto);
  }
  putProducto(producto: Producto): Observable<Producto>{
    return this.http.put<Producto>(this.URL_API, producto);
  }
  getProductoID(id: string): Observable<Producto>{
    return this.http.get<Producto>(`${this.URL_API}/${id}`);
  }
  deleteProducto(id: number | undefined): Observable<any>{
    return this.http.delete(`${this.URL_API}/${id}`);
  }
}
