import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Client } from '../models/client';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  http = inject(HttpClient);
  API = "https://pet-fi-api.onrender.com/clients";

  constructor() { }

  findAll(): Observable<any> {
    return this.http.get(this.API);
  }

  findAllByPage(pageIndex: number): Observable<any> {
    return this.http.get(this.API + "?page=" + pageIndex);
  }

  insert(client: Client): Observable<any> {
    return this.http.post(this.API, client);
  }

  update(id: number, client: Client): Observable<any> {
    return this.http.put(this.API + "/" + id, client);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.API + "/" + id);
  }

  findByPhone(phone: number): Observable<any> {
    return this.http.get(this.API + "/phone/" + phone);
  }

  findByName(name: string): Observable<any> {
    return this.http.get(this.API + "/name/" + name);
  }

  findByNamePaged(value: string, pageIndex: number): Observable<any> {
    return this.http.get(this.API + "/name/" + value + "?page=" + pageIndex);
  }

  findByInstagram(instagram: string): Observable<any> {
    return this.http.get(this.API + "/instagram/" + instagram);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.API + "/" + id);
  }

}
