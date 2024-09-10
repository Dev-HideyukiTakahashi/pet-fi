import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Login } from '../models/login';
import { Observable } from 'rxjs';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { User } from '../models/user';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  http = inject(HttpClient);
  API = `${environment.API}login`;

  constructor() { }

  login(login: Login): Observable<string> {
    return this.http.post<string>(this.API, login, { responseType: 'text' as 'json' });
  }

  addToken(token: string) {
    localStorage.setItem('token', token);
  }

  removeToken() {
    localStorage.removeItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  jwtDecode() {
    let token = this.getToken();
    if (token) {
      return jwtDecode<JwtPayload>(token);
    }
    return "";
  }

  hasPermission(role: string) {
    let user = this.jwtDecode() as User;
    if (user.role == role)
      return true
    else
      return false;
  }

  getUsuarioLogado() {
    return this.jwtDecode() as User;
  }

}


