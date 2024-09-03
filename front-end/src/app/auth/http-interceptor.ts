import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';


export const myHttpInterceptor: HttpInterceptorFn = (request, next) => {

  let router = inject(Router);

  //inclui o token no local storage em cada requisição http (header)
  let token = localStorage.getItem('token');
  if (token && !router.url.includes('/login')) {
    request = request.clone({
      setHeaders: { Authorization: 'Bearer ' + token },
    })
  }

  //tratamento de responses
  return next(request).pipe(
    catchError((err: any) => {

      //Http error
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Usuário ou senha incorretos!",
          });
          router.navigate(["/login"]);
        } else if (err.status === 403) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Necessário logar no sistema para acesso!",
          });
          router.navigate(["/login"]);
        } else {
          console.error('HTTP error: ', err)
        }
      }
      // Erro que não foi da requisição
      else {
        console.error('An error occurred in request: ', err)
      }

      return throwError(() => err);

    })
  )

}
