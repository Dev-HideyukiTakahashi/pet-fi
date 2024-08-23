import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Client } from '../models/client';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  http = inject(HttpClient);
  API = "http://localhost:8080/clients";

  constructor() { }

  // findAll(): Observable<Client[]> {
  //   return this.http.get<Client[]>(this.API);
  // }

  findAll(): Observable<any> {
    return this.http.get(this.API);
  }

  findAllByPage(pageIndex: number): Observable<any> {
    return this.http.get(this.API + "?page=" + pageIndex);
  }

}
