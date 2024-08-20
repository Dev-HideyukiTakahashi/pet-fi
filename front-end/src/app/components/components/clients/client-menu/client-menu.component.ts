
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Client } from '../../../../models/client';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-menu',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './client-menu.component.html',
  styleUrl: './client-menu.component.css'
})
export class ClientMenuComponent {

  clients: Client[] = [];
  client: Client = new Client();
  client2: Client = new Client();


  router = inject(Router);

  constructor() {
    this.client.id = 1;
    this.client.name = "João teste";
    this.client.phone = "111111";
    this.client.instagram = "@teste1"

    this.client2.id = 2;
    this.client2.name = "Maria teste";
    this.client2.phone = "22222";
    this.client2.instagram = "@teste2"
    this.clients.push(this.client);
    this.clients.push(this.client2);
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {

  }
}


