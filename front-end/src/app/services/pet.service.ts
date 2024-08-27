import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Pets } from '../models/pets';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PetService {


  http = inject(HttpClient);
  API = "http://localhost:8080/pets";

  constructor() { }

  insert(clientId: number, pet: Pets): Observable<any> {
    return this.http.post(this.API + "/" + clientId, pet);
  }

  update(petId: number, pet: Pets): Observable<any> {
    return this.http.put(this.API + "/" + petId, pet);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.API + "/" + id);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.API + "/" + id);
  }
}
