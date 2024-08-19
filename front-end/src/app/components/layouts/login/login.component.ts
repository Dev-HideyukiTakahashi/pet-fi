import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  router = inject(Router);
  login!: string;
  password!: string;

  logar() {
    if (this.login == "admin" && this.password == "admin") {
      this.router.navigate(["admin/home/menu/clients"]);
    } else {
      alert("Usuário ou senha inválido!");
    }
  }





}
