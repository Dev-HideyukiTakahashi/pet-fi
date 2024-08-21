import { Component, inject } from '@angular/core';
import { Client } from '../../../../models/client';
import { Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-client-search',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './client-search.component.html',
  styleUrl: './client-search.component.css'
})
export class ClientSearchComponent {

  router = inject(Router);
  client!: Client;
  clients: Client[] = [];
  idClient!: number;
  phoneClient!: number;
  nameClient!: string;
  instagramClient!: string;

  findClientById() {
    if (this.idClient == null) {
      alert("Preencha o campo antes de buscar!");
    }
  }

  findClientByPhone() {
    if (this.phoneClient == null) {
      alert("Preencha o campo antes de buscar! \nTelefone apenas números!");
      location.reload()
    }
  }

  findClientByName() {
    if (this.nameClient == null) {
      alert("Preencha o campo antes de buscar!");
    } else {
      let completeName = this.nameClient.split(" ");
      let firstName = completeName[0];
      let lastName = completeName[1];
    }
  }

  findClientByInstagram() {
    if (this.instagramClient == null) {
      alert("Preencha o campo antes de buscar!");
    } else {
      this.instagramClient = "@" + this.instagramClient;
    }
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {
    this.router.navigate(["admin/home/clients/new"]);
  }
}
