import {HttpClient} from "@angular/common/http";
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";

interface Country {
  name: string;
  value: number;
}
@Injectable({
  providedIn: 'root'
})


export class ServicioService {

  private data: Country[] = [
    {
      "name": "Germany",
      "value": 8940000
    },
    {
      "name": "USA",
      "value": 5000000
    },
    {
      "name": "France",
      "value": 7200000
    },
    {
      "name": "UK",
      "value": 6200000
    }
  ];


  get countryData() {
    return this.data;
  }

  randomData() {
    this.data = [
      {
        "name": "Germany",
        "value": Math.random() * 1000000
      },
      {
        "name": "USA",
        "value": Math.random() * 1000000
      },
      {
        "name": "France",
        "value": Math.random() * 1000000
      },
      {
        "name": "UK",
        "value": Math.random() * 1000000
      }
    ];
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    //direccionar a login
    document.location.href = '/login';

  }
}
