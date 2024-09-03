import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Router } from '@angular/router';
import { Login } from '../../../models/login';
import { LoginService } from '../../../auth/login-service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginService = inject(LoginService);
  router = inject(Router);
  login = new Login();

  constructor() {
    this.loginService.removeToken();
  }

  logar() {

    this.loginService.login(this.login).subscribe({
      next: token => {
        if (token) {
          // usuario e senha corretos, retorna o token e adiciona
          this.loginService.addToken(token);
          // caso tenha mais roles de sistema no futuro adicionar aqui
          if (this.loginService.hasPermission("ADMIN")) {
            this.router.navigate(["/admin/home/clients"]);
          }
        } else {
          alert('Usuario ou senha incorretos!');
        }
      },
      error: err => { console.log(err) },
    })
  }





}
