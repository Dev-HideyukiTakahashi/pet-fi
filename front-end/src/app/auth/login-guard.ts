import { inject, Injectable } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { LoginService } from './login-service';


export const loginGuard: CanActivateFn = (route, state) => {

  let loginService = inject(LoginService);

  if (!loginService.hasPermission("ADMIN") && state.url == "/admin/home/clients") {
    alert("Você não tem permissão de acesso!");
    return false;
  }
  return true;
}
