import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from './login-service';
import Swal from 'sweetalert2';


export const loginGuard: CanActivateFn = (route, state) => {

  let loginService = inject(LoginService);
  let router = inject(Router);

  if (!loginService.hasPermission("ADMIN")) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Necessário logar no sistema para acesso!",
    });
    router.navigate(["/login"]);
    return false;
  }
  return true;
}
