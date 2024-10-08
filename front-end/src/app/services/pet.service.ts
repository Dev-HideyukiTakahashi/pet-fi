import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Pets } from '../models/pets';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PetService {


  http = inject(HttpClient);
  API = "https://pet-fi-api.onrender.com/pets";

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

  findByName(name: string): Observable<any> {
    return this.http.get(this.API + "/name/" + name);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.API + "/" + id);
  }

  findAllPetsWantedAndCity(city: string): Observable<any> {
    return this.http.get(this.API + "/cityWanted/true/" + city);
  }

  findAllPetsWantedAndCityPaged(page: number, city: string): Observable<any> {
    return this.http.get(this.API + "/cityWanted/true/" + city + "?page=" + page);
  }

  findAllPetsWanted(): Observable<any> {
    return this.http.get(this.API + "/wanted/true");
  }

  findAllPetsWantedPaged(page: number): Observable<any> {
    return this.http.get(this.API + "/wanted/true?page=" + page);
  }

  findAllCities(): Observable<any> {
    return this.http.get(this.API + "/cities");
  }



}
