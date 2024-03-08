import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(private router:Router ,private http: HttpClient ) { }
  readonly URL_API = environment.baseUrlAuth + '/authenticate';

  authenticateToken(username: string, password: string): Observable<any>{
    return this.http.post<any> (this.URL_API, {username, password});
  }

  isLogedIn(): boolean{
    if(localStorage.getItem('username') === 'admin'){
      return true;
    }else{
      return false;
    }
  }

  canActivate(){
    if(localStorage.getItem('username') === 'admin'){
      return true;
    }else{
      this.router.navigate(['/login']);
      return false;
    }
  }
}
