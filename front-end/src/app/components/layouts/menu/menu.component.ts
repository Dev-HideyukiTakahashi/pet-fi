import { Component, inject, ViewChild } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { ClientsListComponent } from '../../clients/clients-list/clients-list.component';
import { LoginService } from '../../../auth/login-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [MdbCollapseModule, ClientsListComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  login = inject(LoginService);
  router = inject(Router);

  logout() {
    this.login.removeToken();
    this.router.navigate(['/login'])
  }
}
